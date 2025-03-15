package com.example.weatherapp.ui.reportScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.data.WeatherResponse

@Composable
fun WeatherInfoGrid(weatherData: WeatherResponse?) {
    val weatherInfoList = getWeatherInfoList(weatherData)
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        weatherInfoList.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { weatherInfo ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    )
                    {
                        GlassBox {
                            WeatherInfoRow(
                                icon = weatherInfo.icon,
                                label = weatherInfo.label,
                                value = weatherInfo.value.toString()
                            )
                        }
                    }

                }
            }
        }
    }
}