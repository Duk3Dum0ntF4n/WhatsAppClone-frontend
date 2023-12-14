package com.ocean.whatsappclone.ui

import com.ocean.whatsappclone.domain.ListChat
import com.ocean.whatsappclone.domain.ChatContent

sealed class ListScreenState {

    object Initial:ListScreenState()

    data class Chats(val chats: List<ListChat>) : ListScreenState()

    data class Messages(val chat: ListChat, val messages: List<ChatContent>) : ListScreenState()
}