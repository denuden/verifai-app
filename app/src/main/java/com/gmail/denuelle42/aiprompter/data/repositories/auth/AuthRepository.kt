package com.gmail.denuelle42.aiprompter.data.repositories.auth

import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.LoginRequest
import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.RegisterRequest
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.HelloResponse
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.LoginResponse
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.RegisterResponse
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@ViewModelScoped
class AuthRepository @Inject constructor(
    private val authAPI: AuthAPI,
) {
    suspend fun hello() : HelloResponse {
        val response = authAPI.hello()

        if(response.code() != HttpURLConnection.HTTP_OK){
            throw HttpException(response)
        }
        return response.body() ?: throw NullPointerException("Response data is empty")
    }
    suspend fun login(request : LoginRequest) : LoginResponse {
        val response = authAPI.login(request)

        if(response.code() != HttpURLConnection.HTTP_OK){
            throw HttpException(response)
        }
        return response.body() ?: throw NullPointerException("Response data is empty")
    }

    suspend fun register(request : RegisterRequest) : RegisterResponse {
        val response = authAPI.register(request)

        if(response.code() != HttpURLConnection.HTTP_OK){
            throw HttpException(response)
        }
        return response.body() ?: throw NullPointerException("Response data is empty")
    }

    suspend fun refreshToken() : LoginResponse {
        val response = authAPI.refreshToken()

        if(response.code() != HttpURLConnection.HTTP_OK){
            throw HttpException(response)
        }
        return response.body() ?: throw NullPointerException("Response data is empty")
    }
}