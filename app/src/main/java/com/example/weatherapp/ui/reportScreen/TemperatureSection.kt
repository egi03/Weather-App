package com.example.weatherapp.ui.reportScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.data.WeatherResponse
import kotlin.math.roundToInt

@Composable
fun TemperatureSection(weatherData: WeatherResponse?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = weatherData?.weather?.firstOrNull()?.description.orEmpty(),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Thin
            )
        )
        Text(
            text = " ${weatherData?.main?.temp?.roundToInt()}°",
            style = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.ExtraBold
            ),
            fontSize = 100.sp
        )
        Text(
            text = "Feels like ${weatherData?.main?.feels_like?.roundToInt()}°",
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color(0xFFA0A0A0),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
