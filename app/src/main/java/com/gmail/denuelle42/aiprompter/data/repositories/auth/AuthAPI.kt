package com.gmail.denuelle42.aiprompter.data.repositories.auth

import com.gmail.denuelle42.aiprompter.data.remote.models.UserModel
import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.LoginRequest
import com.gmail.denuelle42.aiprompter.data.repositories.auth.request.RegisterRequest
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.LoginResponse
import com.gmail.denuelle42.aiprompter.data.repositories.auth.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("/v1/auth/login")
    suspend fun login(@Body request : LoginRequest) : Response<LoginResponse>

    @POST("/v1/auth/register")
    suspend fun register(@Body request : RegisterRequest) : Response<RegisterResponse>


}