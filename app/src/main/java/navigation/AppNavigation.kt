package com.example.mesago.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mesago.screens.DetallePlatoScreen
import com.example.mesago.screens.DishFormScreen
import com.example.mesago.screens.HomeScreen
import com.example.mesago.screens.LoginScreen
import com.example.mesago.screens.ManagerMenuScreen
import com.example.mesago.screens.MenuTastyScreen
import com.example.mesago.screens.RegisterScreen
import com.example.mesago.screens.RoleSelectionScreen
import com.example.mesago.screens.SettingsScreen
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
                        popUpTo("splash") { inclusive = true }
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
                    navController.navigate("role_selection") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onLoginClick = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.navigate("role_selection") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("role_selection") {
            RoleSelectionScreen(
                onStudentClick = {
                    navController.navigate("home") {
                        popUpTo("role_selection") { inclusive = true }
                    }
                },
                onManagerClick = {
                    navController.navigate("manager_menu") {
                        popUpTo("role_selection") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(
                onTasty = { tastyId ->
                    navController.navigate("menu/$tastyId")
                },
                onProfileClick = {
                    navController.navigate("settings")
                },
                onSettingsClick = {
                    navController.navigate("settings")
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

        composable("manager_menu") {
            ManagerMenuScreen(
                onAddDish = {
                    navController.navigate("dish_form")
                },
                onEditDish = { dishId ->
                    navController.navigate("dish_form/$dishId")
                },
                onSettings = {
                    navController.navigate("manager_settings")
                },
                onProfileTab = {
                    navController.navigate("manager_settings")
                }
            )
        }

        composable("dish_form") {
            DishFormScreen(
                dishId = null,
                onBack = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }

        composable(
            route = "dish_form/{dishId}",
            arguments = listOf(navArgument("dishId") { type = NavType.StringType })
        ) { entry ->
            val dishId = entry.arguments?.getString("dishId")
            DishFormScreen(
                dishId = dishId,
                onBack = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }

        composable("settings") {
            SettingsScreen(
                isManager = false,
                onBack = { navController.popBackStack() },
                onChangeRole = {
                    navController.navigate("role_selection") {
                        launchSingleTop = true
                    }
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onHomeTab = {
                    navController.navigate("home") {
                        popUpTo("settings") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onTastyTab = {
                    navController.navigate("home") {
                        popUpTo("settings") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable("manager_settings") {
            SettingsScreen(
                isManager = true,
                onBack = { navController.popBackStack() },
                onChangeRole = {
                    navController.navigate("role_selection") {
                        launchSingleTop = true
                    }
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onHomeTab = {
                    navController.navigate("manager_menu") {
                        popUpTo("manager_settings") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onTastyTab = {
                    navController.navigate("manager_menu") {
                        popUpTo("manager_settings") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
