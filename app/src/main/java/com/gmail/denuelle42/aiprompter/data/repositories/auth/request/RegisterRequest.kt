package com.gmail.denuelle42.aiprompter.data.repositories.auth.request

import androidx.annotation.Keep

@Keep
data class LoginRequest(
    val email : String,
    val password : String,
)
