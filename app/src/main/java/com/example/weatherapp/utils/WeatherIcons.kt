package com.example.weatherapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherapp.R
import java.util.Calendar

object WeatherIcons {
    fun getIcon(description: String): Int {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val isDay = hour in 6..18
        return when (description.lowercase()) {
            "thunderstorm with light rain",
            "thunderstorm with rain",
            "thunderstorm with heavy rain",
            "light thunderstorm",
            "thunderstorm",
            "heavy thunderstorm",
            "ragged thunderstorm",
            "thunderstorm with light drizzle",
            "thunderstorm with drizzle",
            "thunderstorm with heavy drizzle" -> (if (isDay) R.drawable.ic_thunderstorm_day else R.drawable.ic_thunderstorm_night)

            "light intensity drizzle",
            "drizzle",
            "heavy intensity drizzle",
            "light intensity drizzle rain",
            "drizzle rain",
            "heavy intensity drizzle rain",
            "shower rain and drizzle",
            "heavy shower rain and drizzle",
            "shower drizzle" -> (if (isDay) R.drawable.ic_drizzle_day else R.drawable.ic_drizzle_night)

            "light rain",
            "moderate rain",
            "heavy intensity rain",
            "very heavy rain",
            "freezing rain",
            "extreme rain" -> R.drawable.ic_rain

            "light intensity shower rain",
            "shower rain",
            "heavy intensity shower rain",
            "ragged shower rain" -> R.drawable.ic_showers_rain

            "light snow",
            "snow",
            "heavy snow",
            "sleet",
            "light shower sleet",
            "shower sleet",
            "light rain and snow",
            "rain and snow",
            "light shower snow",
            "shower snow",
            "heavy shower snow" -> R.drawable.ic_snow

            "mist",
            "smoke",
            "haze",
            "sand/dust whirls",
            "sand",
            "dust",
            "fog" -> R.drawable.ic_fog

            "volcanic ash",
            "squalls",
            "tornado" -> R.drawable.ic_tornado

            "clear sky" -> (if (isDay) R.drawable.ic_clear_day else R.drawable.ic_clear_night)

            "few clouds",
            "scattered clouds",
            "broken clouds",
            "overcast clouds" -> (if (isDay) R.drawable.ic_clouds_day else R.drawable.ic_clouds_night)

            else -> (if (isDay) R.drawable.ic_clouds_day else R.drawable.ic_clouds_night)
        }
    }
}