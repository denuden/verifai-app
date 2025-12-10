package com.gmail.denuelle42.aiprompter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
    }
}
