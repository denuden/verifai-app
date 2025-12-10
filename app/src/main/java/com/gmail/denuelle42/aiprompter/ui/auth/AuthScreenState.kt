package com.gmail.denuelle42.aiprompter.ui.auth

import com.gmail.denuelle42.aiprompter.data.remote.models.UserModel

data class AuthScreenState(
    val email : String? = null,
    val emailError : String? = null,
    val password : String? = null,
    val passwordError : String? = null,
    val passwordConfirmation : String? = null,
    val passwordConfirmationError : String? = null,
    val name : String? = null,
    val nameError : String? = null,

    val isLoginLoading : Boolean = false,
    val isRegisterLoading : Boolean = false,
    val isRefreshTokenLoading : Boolean = false,

    val  userModel: UserModel? = null,
)
