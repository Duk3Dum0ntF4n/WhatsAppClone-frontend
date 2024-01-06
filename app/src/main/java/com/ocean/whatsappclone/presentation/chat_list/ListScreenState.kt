package com.ocean.whatsappclone.presentation.chat_list

import com.ocean.whatsappclone.domain.model.Chat
import com.ocean.whatsappclone.domain.model.Message

sealed class ListScreenState {

    object Initial: ListScreenState()

    data class Chats(val chats: List<Chat>) : ListScreenState()

    data class Messages(val chat: Chat, val messages: List<Message>) : ListScreenState()
}