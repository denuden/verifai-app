package com.gmail.denuelle42.aiprompter.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.denuelle42.aiprompter.utils.OneTimeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel(){
    private val TAG = AuthViewModel::class.java.simpleName

    private val _channel = Channel<OneTimeEvents>()
    val channel = _channel.receiveAsFlow()

    private val _stateFlow = MutableStateFlow<AuthScreenState>(AuthScreenState())
    val stateFlow = _stateFlow.asStateFlow()

    fun onEvent(event : AuthScreenEvents) {
        when(event){
            is AuthScreenEvents.OnNameChange -> {
                _stateFlow.update { it.copy(name = event.value) }
            }
            is AuthScreenEvents.OnEmailChange -> {
                _stateFlow.update { it.copy(email = event.value) }
            }
            is AuthScreenEvents.OnPasswordChange -> {
                _stateFlow.update { it.copy(password = event.value) }
            }
            is AuthScreenEvents.OnLogin -> {
                viewModelScope.launch {
                }
            }
            is AuthScreenEvents.OnRegister -> {
                viewModelScope.launch {
                }
            }
        }
    }

    private fun sendEvent(event: OneTimeEvents) {
        viewModelScope.launch {
            _channel.send(event)
        }
    }
}