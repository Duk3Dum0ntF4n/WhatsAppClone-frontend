package com.ocean.whatsappclone.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocean.whatsappclone.domain.Message
import com.ocean.whatsappclone.domain.Chat

class MainViewModel :ViewModel() {

    private val messages = mutableListOf<Message>().apply {
        repeat(20) {
            add(Message(
                text = "Test",
                formattedTime = "00:00",
                username = "test"
            ))
        }
    }

    private val sourceList = mutableListOf<Chat>().apply {
        repeat(10) {
            add(Chat(
                username = "test"
            ))
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