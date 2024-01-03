package com.ocean.whatsappclone.presentation

sealed class ListScreenState {

    object Initial: ListScreenState()

    data class Chats(val chats: List<com.ocean.whatsappclone.domain.Chat>) : ListScreenState()

    data class Messages(val chat: com.ocean.whatsappclone.domain.Chat, val messages: List<com.ocean.whatsappclone.domain.Message>) : ListScreenState()
}