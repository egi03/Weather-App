package com.example.weatherapp.ui.reportScreen

import com.example.weatherapp.R
import com.example.weatherapp.data.WeatherResponse
import com.example.weatherapp.utils.GeneralHelpers

// Data class for box in the grid
data class WeatherInfo(
    val label: String,
    val value: Any,
    val icon: Int
)

fun getWeatherInfoList(weatherData: WeatherResponse?): List<WeatherInfo> = listOf(

    WeatherInfo(
        "Sunrise",
        GeneralHelpers.formatTimeFromUnix(weatherData?.sys?.sunrise ?: 0.0),
        R.drawable.ic_sunrise),

    WeatherInfo(
        "Sunset",
        GeneralHelpers.formatTimeFromUnix(weatherData?.sys?.sunset ?: 0.0),
        R.drawable.ic_sunset),

    WeatherInfo("Humidity",
        "${weatherData?.main?.humidity ?: 0}%",
        R.drawable.ic_humidity),

    WeatherInfo("Wind Speed",
        "${weatherData?.wind?.speed ?: 0.0} m/s",
        R.drawable.ic_wind_speed),
)