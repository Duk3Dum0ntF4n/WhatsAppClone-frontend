package com.ocean.whatsappclone.data.remote.message

import com.ocean.whatsappclone.domain.Message
import io.ktor.client.HttpClient

class MessageServiceImpl(
    private val client: HttpClient
) : MessageService {

    override suspend fun getAllMessages(): List<Message> {
        TODO("Not yet implemented")
    }
}