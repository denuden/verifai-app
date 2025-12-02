package com.gmail.denuelle42.aiprompter.ui.auth


sealed class AuthScreenEvents {
    data class OnEmailChange(val value : String) : AuthScreenEvents()
    data class OnPasswordChange(val value : String) : AuthScreenEvents()
    data class OnNameChange(val value : String) : AuthScreenEvents()

    //Button
    data class OnLogin(val email : String, val password: String) : AuthScreenEvents()
    data class OnRegister(val name : String, val email : String, val password: String) : AuthScreenEvents()

    //Navigation
}
