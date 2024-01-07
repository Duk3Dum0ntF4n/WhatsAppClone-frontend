package com.ocean.whatsappclone.data.remote.dto

import com.ocean.whatsappclone.domain.model.Chat
import kotlinx.serialization.Serializable

@Serializable
data class ChatDTO(
    val username: String,
    val chatId: String
) {
    fun toChat(): Chat {
        return Chat(
            username = username,
            chatId = chatId
        )
    }
}