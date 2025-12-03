package com.gmail.denuelle42.aiprompter.data.repositories.auth.request

import androidx.annotation.Keep

@Keep
data class RegisterRequest(
    val name : String,
    val email : String,
    val password : String,
    val password_confirmation : String,
)
