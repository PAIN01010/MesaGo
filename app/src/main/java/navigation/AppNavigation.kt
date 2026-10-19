package com.example.mesago.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mesago.screens.DetallePlatoScreen
import com.example.mesago.screens.HomeScreen
import com.example.mesago.screens.LoginScreen
import com.example.mesago.screens.MenuTastyScreen
import com.example.mesago.screens.RegisterScreen
import com.example.mesago.screens.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(
                onFinish = {
                    navController.navigate("login") {
                        popUpTo("splash") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable("login") {
            LoginScreen(
                onRegisterClick = {
                    navController.navigate("register")
                },
                onLoginClick = {
                    navController.navigate("home") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("home") {
            HomeScreen(
                onTasty = { tastyId ->
                    navController.navigate("menu/$tastyId")
                }
            )
        }

        composable("menu/{tastyId}") { entry ->
            val tastyId = entry.arguments?.getString("tastyId")?.toIntOrNull() ?: 0
            MenuTastyScreen(
                tastyId = tastyId,
                onPlato = { platoId ->
                    navController.navigate("detalle/$platoId")
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable("detalle/{platoId}") { entry ->
            val platoId = entry.arguments?.getString("platoId")?.toIntOrNull() ?: 0
            DetallePlatoScreen(
                platoId = platoId,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}