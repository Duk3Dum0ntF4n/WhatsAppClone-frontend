package com.ocean.whatsappclone.domain

data class ChatContent(
    val id: Int,
    val text: String = "Message content text",
    val time: String = "00:00"
)