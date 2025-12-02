package com.gmail.denuelle42.aiprompter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gmail.denuelle42.aiprompter.ui.auth.AuthScreen

fun NavGraphBuilder.addAuthNavGraph(
    navController: NavController
) {
    navigation<RootGraphs.AuthGraph>(startDestination = AuthScreens.AuthMain) {
        composable<AuthScreens.AuthMain> {
            AuthScreen(
                onNavigate = { navController.navigate(it) },
                onPopBackStack = { navController.popBackStack() }
            )
        }
    }
}