package dev.maruffirdaus.w2_androidstudygroup

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.maruffirdaus.w2_androidstudygroup.home.HomeScreen
import dev.maruffirdaus.w2_androidstudygroup.login.LoginScreen
import dev.maruffirdaus.w2_androidstudygroup.register.RegisterScreen
import kotlinx.serialization.Serializable

object Screen {
    @Serializable
    object LoginScreen

    @Serializable
    object RegisterScreen

    @Serializable
    data class HomeScreen(
        val username: String
    )
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen
    ) {
        composable<Screen.LoginScreen> {
            LoginScreen(
                onLogin = { username ->
                    navController.navigate(Screen.HomeScreen(username))
                },
                onRegister = { navController.navigate(Screen.RegisterScreen) }
            )
        }
        composable<Screen.RegisterScreen> {
            RegisterScreen(onBack = { navController.popBackStack() })
        }
        composable<Screen.HomeScreen> { entry ->
            val args = entry.toRoute<Screen.HomeScreen>()
            HomeScreen(
                username = args.username,
                onLogout = { navController.popBackStack() }
            )
        }
    }
}