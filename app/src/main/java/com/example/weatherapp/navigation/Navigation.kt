package com.example.weatherapp.navigation


import OfflineWeatherReportScreen
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ViewModels.WeatherAppViewModel
import com.example.weatherapp.ui.reportScreen.WeatherReportScreen
import com.example.weatherapp.ui.welcomeScreen.WelcomeScreen
import androidx.compose.ui.platform.LocalContext


object Routes {
    const val WELCOME_SCREEN = "welcome"
    const val REPORT_SCREEN = "report"
    const val OFFLINE_SCREEN = "offline"
}

@Composable
fun NavigationController(
    viewModel: WeatherAppViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.WELCOME_SCREEN) {
        composable(Routes.WELCOME_SCREEN) {
            WelcomeScreen(
                viewModel = viewModel(
                    factory = ViewModelProvider.AndroidViewModelFactory.getInstance(LocalContext.current.applicationContext as Application)
                ),
                navController = navController
            )
        }
        composable(Routes.REPORT_SCREEN) {
            WeatherReportScreen(navController = navController)
        }
        composable(Routes.OFFLINE_SCREEN) {
            OfflineWeatherReportScreen(navController = navController)
        }
    }
}
