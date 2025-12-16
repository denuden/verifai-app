package com.gmail.denuelle42.aiprompter.domain.repositories.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.gmail.denuelle42.aiprompter.data.repositories.auth.AuthRepository
import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.LoginRequest
import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.RegisterRequest
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.HelloResponse
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.LoginResponse
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.RegisterResponse
import com.gmail.denuelle42.aiprompter.di.modules.IoDispatcher
import com.gmail.denuelle42.aiprompter.utils.security.UserPreferences
import com.gmail.denuelle42.aiprompter.utils.security.UserPreferencesSerializer
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStore: DataStore<UserPreferences>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) {

    fun hello() : Flow<HelloResponse> {
        return flow {
            val response = authRepository.hello()
            emit(response)
        }.flowOn(ioDispatcher)
    }

    fun login(request: LoginRequest) : Flow<LoginResponse> {
        return flow {
            val response = authRepository.login(request)
            val token = response.token.orEmpty().ifEmpty { "n/a" }
            dataStore.updateData {
                UserPreferences(token = token)
            }
            emit(response)
        }.flowOn(ioDispatcher)
    }

    fun register(request: RegisterRequest) : Flow<RegisterResponse> {
        return flow {
            val response = authRepository.register(request)
            val token = response.token.orEmpty().ifEmpty { "n/a" }
            dataStore.updateData {
                UserPreferences(token = token)
            }
            emit(response)
        }.flowOn(ioDispatcher)
    }

    fun refreshToken() : Flow<LoginResponse> {
        return flow {
            val response = authRepository.refreshToken()
            val token = response.token.orEmpty().ifBlank { "n/a" }
            dataStore.updateData {
                UserPreferences(token = token)
            }
            emit(response)
        }.flowOn(ioDispatcher)
    }

}