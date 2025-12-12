package com.gmail.denuelle42.aiprompter.ui.fact_check

import com.gmail.denuelle42.aiprompter.data.repositories.fact_check.request.CreateFactCheckRequest

sealed class FactCheckScreenEvents {
    data class OnChangeTextPrompt(val value : String) : FactCheckScreenEvents()

    data class OnCreateFactCheck(val request : CreateFactCheckRequest) : FactCheckScreenEvents()

    data class OnNavigateToChatScreen(val value: String) : FactCheckScreenEvents()
    object OnNavigateToPromptScreen : FactCheckScreenEvents()
}