package com.gmail.denuelle42.aiprompter.ui.fact_check

sealed class FactCheckScreenEvents {
    data class OnChangeTextPrompt(val value : String) : FactCheckScreenEvents()

}