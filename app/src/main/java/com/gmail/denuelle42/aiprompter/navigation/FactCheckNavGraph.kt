package com.gmail.denuelle42.aiprompter.navigation

import ChatScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.gmail.denuelle42.aiprompter.ui.fact_check.screen.PromptScreen

fun NavGraphBuilder.addFactCheckNavGraph(
    navController: NavController
) {
    navigation<RootGraphs.FactCheckGraph>(startDestination = FactCheckScreens.PromptNavigation) {
        composable<FactCheckScreens.PromptNavigation> {
            PromptScreen(
                onNavigate = { navController.navigate(it) },
                onPopBackStack = { navController.popBackStack() }
            )
        }

        composable<FactCheckScreens.ChatNavigation> {
            val args = it.toRoute<FactCheckScreens.ChatNavigation>()
            val statement = args.statement

            ChatScreen(
                onNavigate = { route -> navController.navigate(route) },
                onPopBackStack = { navController.popBackStack() },
                statement = statement
            )
        }
    }
}
