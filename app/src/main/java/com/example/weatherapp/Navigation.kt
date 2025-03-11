package com.example.weatherapp


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.data.WeatherAppViewModel
import com.example.weatherapp.ui.WelcomeScreen

object Routes {
    const val WELCOME_SCREEN = "welcomeScreen"
}

@Composable
fun NavigationController(
    viewModel: WeatherAppViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.WELCOME_SCREEN) {
        composable(Routes.WELCOME_SCREEN) {
            WelcomeScreen()
        }
    }
}