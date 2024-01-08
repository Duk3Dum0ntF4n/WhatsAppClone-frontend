package com.ocean.whatsappclone.data.remote.service

import com.ocean.whatsappclone.data.remote.dto.ChatDTO
import com.ocean.whatsappclone.data.remote.dto.MessageReceiveDTO
import com.ocean.whatsappclone.data.remote.dto.MessageResponseDTO
import com.ocean.whatsappclone.domain.model.Chat
import com.ocean.whatsappclone.domain.model.Message
import com.ocean.whatsappclone.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MessengerSocketServiceImpl(
    private val client: HttpClient
) : MessengerSocketService {

    private var socket: WebSocketSession? = null

    override suspend fun initSession(username: String): Resource<Unit> {
        return try {
            socket = client.webSocketSession {
                url("${MessengerSocketService.Endpoints.MessengerSocket.url}?username=$username")
            }
            if (socket?.isActive == true) {
                Resource.Success(Unit)
            } else Resource.Error("Could not establish connection")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun sendMessage(text: String, chatId: String) {
        try {
            val message = Json.encodeToString(MessageResponseDTO(text, chatId))
            socket?.send(Frame.Text(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessages(): Flow<Message> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as? Frame.Text)?.readText() ?: ""
                    val messageDTO = Json.decodeFromString<MessageReceiveDTO>(json)
                    messageDTO.toMessage()
                } ?: flow { }
        } catch (e: Exception) {
            e.printStackTrace()
            flow { }
        }
    }

    override suspend fun getChatMessages(chatId: String): List<Message> {
        return try {
            client.get<List<MessageReceiveDTO>>("${MessengerSocketService.Endpoints.GetAllMessages.url}/$chatId")
                .map { it.toMessage() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getAllChats(username: String): List<Chat> {
        return try {
            client.get<List<ChatDTO>>("${MessengerSocketService.Endpoints.GetAllChats.url}/$username")
                .map { it.toChat() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }

}