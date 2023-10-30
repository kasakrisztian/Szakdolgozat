package com.example.szakdolgozat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.szakdolgozat.screens.detail.GameDetailScreen
import com.example.szakdolgozat.screens.main.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "games"
    ) {
        composable(route = "games") {
            MainScreen(
                toDetailScreen = { id ->
                    navController.navigate("game-detail/$id")
                }
            )
        }
        composable(
            route = "game-detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            GameDetailScreen(
                toBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}