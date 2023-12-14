package com.ocean.whatsappclone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocean.whatsappclone.domain.ChatContent
import com.ocean.whatsappclone.domain.ListChat
import com.ocean.whatsappclone.ui.ListScreenState

class MainViewModel :ViewModel() {

    private val messages = mutableListOf<ChatContent>().apply {
        repeat(20) {
            add(ChatContent(id = it))
        }
    }

    private val sourceList = mutableListOf<ListChat>().apply {
        repeat(10) {
            add(ListChat(chat_id = it))
        }
    }
    private val initialState = ListScreenState.Chats(sourceList)
    private val _screenState = MutableLiveData<ListScreenState>(initialState)
    val screenState: LiveData<ListScreenState> = _screenState

    private var savedState: ListScreenState? = initialState

    fun showChatMessages(listChat: ListChat) {
        savedState = _screenState.value
        _screenState.value = ListScreenState.Messages(chat = listChat, messages = messages)
    }

    fun closeComments() {
        _screenState.value = savedState
    }
}