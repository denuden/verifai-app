package com.gmail.denuelle42.aiprompter.ui.sample

sealed class SampleScreenEvents {
    data class OnGetEvent(val name : String) : SampleScreenEvents()
}