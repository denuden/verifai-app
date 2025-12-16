package com.gmail.denuelle42.aiprompter.navigation

import com.gmail.denuelle42.aiprompter.data.remote.models.SampleModel
import com.gmail.denuelle42.aiprompter.data.remote.models.fact_check.FactCheckModel
import kotlinx.serialization.Serializable

/**
 * For splashscreen and simple navigation
 */
sealed interface AppRootScreens {
    @Serializable
    data object Splash : AppRootScreens

    @Serializable
    data class Main(val isLoggedIn: Boolean) : AppRootScreens
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
    @Serializable
    data object FactCheckGraph : RootGraphs()
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
    data object AuthMainNavigation : AuthScreens()
    @Serializable
    data object LoginNavigation : AuthScreens()
    @Serializable
    data object RegisterNavigation : AuthScreens()

    //Not part, just a holder to indicate that i am in splashscreen
    @Serializable
    data object SplashNavigation : AuthScreens()
}

sealed class FactCheckScreens : NavigationScreens {
    @Serializable
    data object PromptNavigation : FactCheckScreens()
    @Serializable
    data class ChatNavigation(val statement: String) : FactCheckScreens()
    @Serializable
    data object HistoryNavigation : FactCheckScreens()
}
