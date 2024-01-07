package com.ocean.whatsappclone.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponseDTO(
    val text: String,
    val chatId: String
)
