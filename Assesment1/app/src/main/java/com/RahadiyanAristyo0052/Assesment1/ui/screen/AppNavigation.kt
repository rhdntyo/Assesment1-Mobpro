package com.RahadiyanAristyo0052.Assesment1.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            MainScreen(
                onOpenAbout = {
                    navController.navigate("about") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable("about") {
            AboutScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
