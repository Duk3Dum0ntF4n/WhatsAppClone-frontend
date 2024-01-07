package com.ocean.whatsappclone.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocean.whatsappclone.data.remote.service.MessengerSocketService
import com.ocean.whatsappclone.domain.model.Chat
import com.ocean.whatsappclone.domain.model.Message
import com.ocean.whatsappclone.util.Resource
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Collections.addAll
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val messengerSocketService: MessengerSocketService
) : ViewModel() {

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    private val _currentChat = mutableStateOf("")
    private val currentChat: State<String> = _currentChat

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _onChatClicked = MutableSharedFlow<String>()
    val onChatClicked = _onChatClicked.asSharedFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun setUsername(username: String) {
        _username.value = username
    }

    fun connect() {
        getAllMessages(username.value)
        viewModelScope.launch {
            when (val result = messengerSocketService.initSession(username.value)) {
                is Resource.Success -> {
                    messengerSocketService.observeMessages()
                        .onEach { message ->
                            val newMessageList = state.value.messages.toMutableList().apply {
                                add(0, message)
                            }
                            _state.value = state.value.copy(
                                messages = newMessageList
                            )
                            createChat(message)
                        }
                }

                is Resource.Error -> {
                    _toastEvent.emit(result.message ?: "Unknown error")
                }
            }
        }
    }


    private fun getAllMessages(username: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val chatResult = messengerSocketService.getAllChats(username)
            val messageResult = mutableListOf<Message>()
            chatResult.forEach { chat ->
                val chatMessages = messengerSocketService.getChatMessages(chat.chatId)
                messageResult.addAll(chatMessages)
            }
            _state.value = state.value.copy(
                chats = chatResult,
                messages = messageResult,
                isLoading = false
            )
        }
    }

    private fun createChat(message: Message) {
        if (state.value.chats.firstOrNull { it.chatId == message.chatId } == null) {
            val newChatList = state.value.chats.toMutableList().apply {
                add(
                    0, Chat(username = message.username, chatId = message.chatId)
                )
            }
            _state.value = state.value.copy(
                chats = newChatList
            )
        }
    }

    fun onChatClicked(string: String) {
        viewModelScope.launch {
            _onChatClicked.emit(string)
            _currentChat.value = string
        }
    }

    fun onMessageChange(message: String) {
        _messageText.value = message
    }

    fun sendMessage() {
        viewModelScope.launch {
            if (messageText.value.isNotBlank()) {
                messengerSocketService.sendMessage(
                    text = messageText.value,
                    chatId = currentChat.value
                )
            }
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            messengerSocketService.closeSession()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }

}