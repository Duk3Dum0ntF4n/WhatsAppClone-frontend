package com.ocean.whatsappclone.data.remote.messenger

import com.ocean.whatsappclone.domain.Chat
import com.ocean.whatsappclone.domain.Message
import com.ocean.whatsappclone.util.Resource
import kotlinx.coroutines.flow.Flow

interface MessengerSocketService {

    suspend fun initSession(
        username: String
    ): Resource<Unit>

    suspend fun sendMessage(message: String, username: String)

    fun observeChats() : Flow<Chat>

    fun observeMessages(username: String) : Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://10.0.0.2:8080"
    }

    sealed class Endpoints(val url: String) {
        object MessengerSocket: Endpoints("$BASE_URL/chats")
    }
}