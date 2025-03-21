package com.example.weatherapp.ui.welcomeScreen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.navigation.Routes
import com.example.weatherapp.ViewModels.WelcomeScreenViewModel
import com.example.weatherapp.database.SharedPrefsManager
import com.example.weatherapp.utils.NetworkObserver
import kotlinx.coroutines.launch


@Composable
fun WelcomeScreen(
    viewModel: WelcomeScreenViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(LocalContext.current.applicationContext as Application)
    ),
    navController: NavController
) {
    var searchedCity by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    val previousSearches by viewModel.previousSearches.collectAsState()
    val background = if (isSystemInDarkTheme()) R.drawable.background_dark else R.drawable.background_light
    val context = LocalContext.current


    val networkObserver = remember { NetworkObserver(context) }
    val isOnline by networkObserver.isOnline.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            networkObserver.cleanup()
        }
    }


    BackgroundBox(background) {
        if (!isOnline) {
            OfflineMainContent(
                previousSearches = previousSearches,
                navController = navController,
                viewModel = viewModel
            )
        }
        else {
            MainContent(
                searchedCity = searchedCity,
                onSearchChange = { searchedCity = it },
                isLoading = isLoading,
                previousSearches = previousSearches,
                onSearch = {
                    coroutineScope.launch {
                        try {
                            isLoading = true
                            val (weatherData, forecastData) = viewModel.getAllWeatherData(
                                searchedCity.text
                            )
                            when {
                                weatherData == null || forecastData == null -> {
                                    Toast.makeText(context, "Invalid location", Toast.LENGTH_SHORT)
                                        .show()

                                }

                                weatherData.cod == 200 && forecastData.cod == "200" -> {
                                    SharedPrefsManager(context).saveLocation(
                                        searchedCity.text,
                                        weatherData,
                                        forecastData
                                    )
                                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                                        set("weatherData", weatherData)
                                        set("forecastData", forecastData)
                                    }
                                    navController.navigate(Routes.REPORT_SCREEN)
                                    searchedCity = TextFieldValue("")
                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(context, "Invalid location", Toast.LENGTH_SHORT).show()
                        } finally {
                            isLoading = false
                        }
                    }
                },
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

