package com.gmail.denuelle42.aiprompter.utils

import com.gmail.denuelle42.aiprompter.navigation.NavigationScreens

/**
 * One time events that are only triggered once, to be used in Channel
 */
sealed class OneTimeEvents {
    data class OnNavigate(val route : NavigationScreens) : OneTimeEvents()
    object OnPopBackStack : OneTimeEvents()
    data class ShowSnackbar(val snackbarEvent: SnackbarEvent)  : OneTimeEvents()
    data class ShowToast(val message : String)  : OneTimeEvents()
}