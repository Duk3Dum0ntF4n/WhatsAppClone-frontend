package com.ocean.whatsappclone.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _onJoinMessenger = MutableSharedFlow<String>()
    val onJoinMessenger = _onJoinMessenger.asSharedFlow()

    fun onUsernameChange(username: String) {
        _usernameText.value = username
    }

    fun onJoinClicked() {
        viewModelScope.launch {
            if(usernameText.value.isNotBlank()) {
                _onJoinMessenger.emit(usernameText.value)
            }
        }
    }

}