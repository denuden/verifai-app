package com.gmail.denuelle42.aiprompter.navigation

import android.window.SplashScreen
import com.gmail.denuelle42.aiprompter.data.remote.models.SampleModel
import kotlinx.serialization.Serializable

/**
 * For splashscreen and simple navigation
 */
enum class AppRootScreens{
    Splash,
    Main
}

/**
 * Main Destinations for nested graph, like a web link  E.G. auth/login, auth/register. main/home. main/profile
 * article/home, article/view
 */
sealed class RootGraphs {
    @Serializable
    data object SampleGraph : RootGraphs()
    @Serializable
    data object AuthGraph : RootGraphs()
}

/**
 *  General or shared type of all screens
 */
sealed interface NavigationScreens

sealed class SampleScreens : NavigationScreens {
    @Serializable
    data object SampleNavigation : SampleScreens()
    @Serializable
    data class SampleDetailsNavigation(val sampleModel: SampleModel) : SampleScreens()
}

sealed class AuthScreens : NavigationScreens {
    @Serializable
    data object AuthMain : AuthScreens()
    @Serializable
    data object Login : AuthScreens()
    @Serializable
    data object Register : AuthScreens()
}
