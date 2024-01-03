package com.ocean.whatsappclone.data.remote.chat

import com.ocean.whatsappclone.domain.Chat

interface ChatService {

    suspend fun getChats() :List<Chat>

    companion object {
        const val BASE_URL = "http://10.0.0.2"
    }

    sealed class Endpoints(val url: String) {
        object GetAllChats: Endpoints("$BASE_URL/chats")
    }

}