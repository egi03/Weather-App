package com.example.weatherapp.ui.reportScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.utils.WeatherIcons
import kotlin.math.roundToInt

@Composable
fun HourlyForecast(forecastData: ForecastResponse) {
    Column {
        Text(
            text = "Hourly Forecast",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(forecastData.list.take(24)) { forecast ->
                GlassBox {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = forecast.dt_txt.substring(11, 16),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 16.sp
                        )
                        Image(
                            painter = painterResource(id = WeatherIcons.getIcon(forecast.weather.first().description)),
                            contentDescription = "Weather icon",
                            Modifier.size(30.dp)
                        )
                        Text(
                            text = "${forecast.main.temp.roundToInt()}°",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}