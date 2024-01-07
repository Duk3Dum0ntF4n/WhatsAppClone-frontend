package com.ocean.whatsappclone.data.remote.service

import com.ocean.whatsappclone.domain.model.Chat
import com.ocean.whatsappclone.domain.model.Message
import com.ocean.whatsappclone.util.Resource
import kotlinx.coroutines.flow.Flow

interface MessengerSocketService {
    suspend fun initSession(
        username: String
    ): Resource<Unit>

    suspend fun sendMessage(text: String, chatId: String)

    suspend fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    suspend fun getAllMessages(chatId: String): List<Message>

    suspend fun getAllChats(username: String): List<Chat>

    companion object {
        const val BASE_WS_URL = "ws://10.0.2.2:8080"
        const val BASE_HTTP_URL = "http://10.0.2.2:8080"
    }

    sealed class Endpoints(val url: String) {
        object MessengerSocket : Endpoints("$BASE_WS_URL/message")
        object GetAllMessages : Endpoints("$BASE_HTTP_URL/message")
        object GetAllChats : Endpoints("$BASE_HTTP_URL/chat")
    }
}