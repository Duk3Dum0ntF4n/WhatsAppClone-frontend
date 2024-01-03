package com.ocean.whatsappclone.data.remote.message

import com.ocean.whatsappclone.data.remote.dto.MessageDTO
import com.ocean.whatsappclone.domain.Message
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class MessageServiceImpl(
    private val client: HttpClient
) : MessageService {

    override suspend fun getAllMessages(): List<Message> {
        return try {
            client.get<List<MessageDTO>>(MessageService.Endpoints.GetAllMessages.url)
                .map { it.toMessage() }
        } catch (e:Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}