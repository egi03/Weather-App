package com.example.weatherapp.ui.welcomeScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.navigation.Routes
import com.example.weatherapp.ViewModels.WelcomeScreenViewModel
import com.example.weatherapp.database.SharedPrefsManager
import com.example.weatherapp.utils.GeneralHelpers.capitalizeWords
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LocationCard(text: String,
                 navController: NavController,
                 viewModel: WelcomeScreenViewModel,
                 isOnline: Boolean = true
) {
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color  = MaterialTheme.colorScheme.scrim,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .clickable(onClick = {
                if (isOnline){
                    handleOnlineClick(text, coroutineScope, viewModel, navController, context)
                } else {
                    handleOfflineClick(text, navController, context)
                }
            })


    ) {
        Text(
            text = text.capitalizeWords(),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
    }
    Spacer(modifier = Modifier.height(16.dp))

}



private fun handleOnlineClick(
    text: String,
    coroutineScope: CoroutineScope,
    viewModel: WelcomeScreenViewModel,
    navController: NavController,
    context: Context
) {
    coroutineScope.launch {
        try {
            val (weatherData, forecastData) = viewModel.getAllWeatherData(text)
            when {
                weatherData == null || forecastData == null -> {
                    Toast.makeText(context, "Failed to fetch weather data", Toast.LENGTH_SHORT).show()
                }
                weatherData.cod == 200 && forecastData.cod == "200" -> {
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set("weatherData", weatherData)
                        set("forecastData", forecastData)
                    }
                    navController.navigate(Routes.REPORT_SCREEN)
                }
                else -> {
                    Toast.makeText(context, "City not found", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun handleOfflineClick(
    text: String,
    navController: NavController,
    context: Context
) {
    val sharedPrefsManager = SharedPrefsManager(context)
    val weatherData = sharedPrefsManager.getWeather(text)
    val forecastData = sharedPrefsManager.getForecast(text)
    val dateTime = sharedPrefsManager.getDateTime(text)

    if (weatherData != null && forecastData != null) {
        navController.currentBackStackEntry?.savedStateHandle?.apply {
            set("weatherData", weatherData)
            set("forecastData", forecastData)
            set("dateTime", dateTime)
        }
        navController.navigate(Routes.OFFLINE_SCREEN)
    } else {
        Toast.makeText(context, "No cached data available", Toast.LENGTH_SHORT).show()
    }
}