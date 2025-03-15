package com.example.weatherapp


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ViewModels.WeatherAppViewModel
import com.example.weatherapp.ViewModels.WelcomeScreenViewModel
import com.example.weatherapp.ui.reportScreen.WeatherReportScreen
import com.example.weatherapp.ui.WelcomeScreen

object Routes {
    const val WELCOME_SCREEN = "welcome"
    const val REPORT_SCREEN = "report"
}

@Composable
fun NavigationController(
    viewModel: WeatherAppViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.WELCOME_SCREEN) {
        composable(Routes.WELCOME_SCREEN) {
            WelcomeScreen(viewModel = WelcomeScreenViewModel(), navController = navController)
        }
        composable(Routes.REPORT_SCREEN) {
                WeatherReportScreen(navController = navController)
            }
        }
    }
