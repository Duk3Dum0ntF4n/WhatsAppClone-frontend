package com.ocean.whatsappclone.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocean.whatsappclone.data.remote.service.MessengerSocketService
import com.ocean.whatsappclone.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val messengerSocketService: MessengerSocketService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun connect() {
        savedStateHandle.get<String>("username")?.let { username ->
            getAllMessages(username)
            viewModelScope.launch {
                when (val result = messengerSocketService.initSession(username)) {
                    is Resource.Success -> {
                        messengerSocketService.observeMessages()
                            .onEach { message ->
                                val newList = state.value.messages.toMutableList().apply {
                                    add(0, message)
                                }
                                _state.value = state.value.copy(
                                    messages = newList
                                )
                            }
                    }

                    is Resource.Error -> {
                        _toastEvent.emit(result.message ?: "Unknown error")
                    }
                }
            }
        }
    }

    private fun getAllMessages(username: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(isLoading = true)
            val result = messengerSocketService.getAllMessages(username)
            _state.value = state.value.copy(
                messages = result,
                isLoading = false
            )
        }
    }

    fun sendMessage(chatId: String) {
        viewModelScope.launch {
            if (messageText.value.isNotBlank()) {
                messengerSocketService.sendMessage(
                    text = messageText.value,
                    chatId = chatId
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