package com.ocean.whatsappclone.data.remote.messenger

import com.ocean.whatsappclone.domain.Chat
import com.ocean.whatsappclone.domain.Message
import com.ocean.whatsappclone.util.Resource
import io.ktor.client.HttpClient
import io.ktor.http.cio.websocket.WebSocketSession
import kotlinx.coroutines.flow.Flow

class MessengerSocketServiceImpl(
    private val client: HttpClient
) : MessengerSocketService {

    private val socket: WebSocketSession? = null

    override suspend fun initSession(username: String): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(message: String, username: String) {
        TODO("Not yet implemented")
    }

    override fun observeChats(): Flow<Chat> {
        TODO("Not yet implemented")
    }

    override fun observeMessages(username: String): Flow<Message> {
        TODO("Not yet implemented")
    }

    override suspend fun closeSession() {
        TODO("Not yet implemented")
    }
}