package com.ocean.whatsappclone.presentation

import com.ocean.whatsappclone.domain.model.Chat
import com.ocean.whatsappclone.domain.model.Message

data class MainState(
    val chats: List<Chat> = emptyList(),
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)