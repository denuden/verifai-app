package com.gmail.denuelle42.denuboilerplate.ui.sample

sealed class SampleScreenEvents {
    data class OnGetEvent(val name : String) : SampleScreenEvents()
}