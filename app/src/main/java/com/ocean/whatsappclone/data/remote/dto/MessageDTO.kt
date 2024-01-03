package com.ocean.whatsappclone.data.remote.dto

import com.ocean.whatsappclone.domain.Message
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.util.Date

@Serializable
data class MessageDTO(
    val text: String,
    val timestamp: Long,
    val username: String,
    val id: String
) {
    fun toMessage() : Message {
        val date = Date(timestamp)
        val formattedDate = DateFormat
            .getDateInstance()
            .format(date)
        return Message(
            text = text,
            formattedTime = formattedDate,
            username = username
        )
    }
}