package com.ocean.whatsappclone.data.remote.chat

import com.ocean.whatsappclone.domain.Chat
import io.ktor.client.HttpClient

class ChatServiceImpl(
    private val client: HttpClient
) : ChatService {

    override suspend fun getChats(): List<Chat> {
        TODO("Not yet implemented")
    }
}