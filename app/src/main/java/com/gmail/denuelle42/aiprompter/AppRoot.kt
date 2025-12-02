package com.gmail.denuelle42.aiprompter

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gmail.denuelle42.aiprompter.navigation.AppRootScreens

@Composable
fun AppRoot() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRootScreens.Splash.toString()
    ) {
        composable(AppRootScreens.Splash.toString()) {
            SplashScreen(
                onFinished = {
                    navController.navigate(AppRootScreens.Main.toString()) {
                        popUpTo(AppRootScreens.Splash.toString()) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRootScreens.Main.toString()) {
            MainScreen()
        }
    }
}
