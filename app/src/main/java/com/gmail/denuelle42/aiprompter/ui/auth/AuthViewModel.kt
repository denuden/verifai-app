package com.gmail.denuelle42.aiprompter.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.denuelle42.aiprompter.data.remote.error.ErrorModel
import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.LoginRequest
import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.RegisterRequest
import com.gmail.denuelle42.aiprompter.domain.repositories.auth.AuthUseCase
import com.gmail.denuelle42.aiprompter.navigation.AuthScreens
import com.gmail.denuelle42.aiprompter.navigation.FactCheckScreens
import com.gmail.denuelle42.aiprompter.utils.OneTimeEvents
import com.gmail.denuelle42.aiprompter.utils.network.ResultState
import com.gmail.denuelle42.aiprompter.utils.network.asResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {
    private val TAG = AuthViewModel::class.java.simpleName

    private val _channel = Channel<OneTimeEvents>()
    val channel = _channel.receiveAsFlow()

    private val _stateFlow = MutableStateFlow<AuthScreenState>(AuthScreenState())
    val stateFlow = _stateFlow.asStateFlow()

    fun onEvent(event: AuthScreenEvents) {
        when (event) {
            is AuthScreenEvents.OnNameChange -> {
                _stateFlow.update { it.copy(name = event.value, nameError = null) }
            }
            is AuthScreenEvents.OnEmailChange -> {
                _stateFlow.update { it.copy(email = event.value, emailError = null) }
            }
            is AuthScreenEvents.OnPasswordChange -> {
                _stateFlow.update { it.copy(password = event.value, passwordError = null) }
            }
            is AuthScreenEvents.OnPasswordConfirmationChange -> {
                _stateFlow.update { it.copy(passwordConfirmation = event.value, passwordConfirmationError = null) }
            }

            is AuthScreenEvents.OnLogin -> {
                viewModelScope.launch {
                    authUseCase.login(
                        LoginRequest(
                            email = event.email,
                            password = event.password
                        )
                    ).asResult().onEach { res ->
                        when (res) {
                            ResultState.Completed -> _stateFlow.update { it.copy(isLoginLoading = false) }
                            is ResultState.Error -> onError(res.exception)
                            ResultState.Loading -> _stateFlow.update { it.copy(isLoginLoading = true) }
                            is ResultState.Success -> {
                                _stateFlow.update { it.copy(userModel = res.data.data) }
                                sendEvent(OneTimeEvents.ShowToast("Hello ${res.data.data?.name}"))

                                //navigate to home
                                sendEvent(OneTimeEvents.OnNavigate(FactCheckScreens.PromptNavigation))
                            }
                        }
                    }.collect()
                }
            }

            is AuthScreenEvents.OnRegister -> {
                viewModelScope.launch {
                    authUseCase.register(
                        RegisterRequest(
                            name = event.name,
                            email = event.email,
                            password = event.password,
                            password_confirmation = event.password_confirmation
                        )
                    ).asResult().onEach { res ->
                        when (res) {
                            ResultState.Completed -> _stateFlow.update { it.copy(isRegisterLoading = false) }
                            is ResultState.Error -> onError(res.exception)
                            ResultState.Loading -> _stateFlow.update { it.copy(isRegisterLoading = true) }
                            is ResultState.Success -> {
                                _stateFlow.update { it.copy(userModel = res.data.data) }
                                sendEvent(OneTimeEvents.ShowToast("Hello ${res.data.data?.name}"))

                                //navigate to home
                                sendEvent(OneTimeEvents.OnNavigate(FactCheckScreens.PromptNavigation))
                            }
                        }
                    }.collect()
                }
            }

            is AuthScreenEvents.OnRefreshToken -> {
                viewModelScope.launch {
                    authUseCase.refreshToken().asResult().onEach { res ->
                        when (res) {
                            ResultState.Completed -> _stateFlow.update { it.copy(isRefreshTokenLoading = false) }
                            is ResultState.Error -> onError(res.exception)
                            ResultState.Loading -> _stateFlow.update { it.copy(isRefreshTokenLoading = true) }
                            is ResultState.Success -> {
                                sendEvent(OneTimeEvents.OnNavigate(AuthScreens.SplashNavigation))
                            }
                        }
                    }.collect()
                }
            }
        }
    }

    private fun onError(e: Throwable?) {
        when (e) {
            is HttpException -> {
                val statusCode = e.code()
                val errorBody = e.response()?.errorBody()
                val gson = Gson()
                val type = object : TypeToken<ErrorModel>() {}.type
                val errorResponse: ErrorModel? = gson.fromJson(errorBody?.charStream(), type)

                // Example: Handle specific status
                when(statusCode) {
                    401 -> {
                        sendEvent(OneTimeEvents.OnNavigate(AuthScreens.AuthMainNavigation))
                        return
                    }
                }

                //if this is not null, then there is a message regarding bad request of params
                if (errorResponse?.errors != null) {
                    _stateFlow.update {
                        it.copy(
                            emailError = errorResponse.errors.email?.get(0),
                            passwordError = errorResponse.errors.password?.get(0),
                            nameError = errorResponse.errors.name?.get(0),
                            passwordConfirmationError = errorResponse.errors.password_confirmation?.get(0),
                        )
                    }
                    sendEvent(OneTimeEvents.ShowInputError(errorResponse.errors))
                } else if (errorResponse?.message != null) {
                    sendEvent(OneTimeEvents.ShowError(errorResponse.message))
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