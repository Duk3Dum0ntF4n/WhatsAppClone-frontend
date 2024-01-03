package com.ocean.whatsappclone.data.remote.message

import com.ocean.whatsappclone.domain.Message

interface MessageService {

    suspend fun getAllMessages() : List<Message>

    companion object {
        const val BASE_URL = "http://10.0.0.2:8080"
    }

    sealed class Endpoints(val url: String) {

        object GetAllMessages: Endpoints("$BASE_URL/messages")

    }

}