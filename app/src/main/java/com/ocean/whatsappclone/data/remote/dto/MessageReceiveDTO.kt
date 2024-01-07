package com.ocean.whatsappclone.data.remote.dto

import com.ocean.whatsappclone.domain.model.Message
import kotlinx.serialization.Serializable

@Serializable
data class MessageReceiveDTO(
    val username: String,
    val text: String,
    val chatId: String
) {
    fun toMessage(): Message {
        return Message(
            username = username,
            text = text,
            chatId = chatId
        )
    }
}