package com.ocean.whatsappclone.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocean.whatsappclone.domain.model.Message
import com.ocean.whatsappclone.domain.model.Chat
import com.ocean.whatsappclone.presentation.chat_list.ListScreenState

class MainViewModel :ViewModel() {

    private val messages = mutableListOf<Message>().apply {
        repeat(20) {
            add(
                Message(
                text = "Test",
                username = "test",
                    chatId = "1"
            )
            )
        }
    }

    private val sourceList = mutableListOf<Chat>().apply {
        repeat(10) {
            add(
                Chat(
                username = "test",
                    chatId = "1"
            )
            )
        }
    }
    private val initialState = ListScreenState.Chats(sourceList)
    private val _screenState = MutableLiveData<ListScreenState>(initialState)
    val screenState: LiveData<ListScreenState> = _screenState

    private var savedState: ListScreenState? = initialState

    fun showChatMessages(listChat: Chat) {
        savedState = _screenState.value
        _screenState.value = ListScreenState.Messages(chat = listChat, messages = messages)
    }

    fun closeComments() {
        _screenState.value = savedState
    }
}