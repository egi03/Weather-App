package com.example.weatherapp.ui.reportScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.utils.GeneralHelpers
import com.example.weatherapp.utils.WeatherIcons
import kotlin.math.roundToInt

@Composable
fun DailyForecast(forecastData: ForecastResponse) {
    Column {
        Text(
            text = "5-Day Forecast",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(16.dp)
        )
        forecastData.list.filterIndexed { index, _ ->
            index % 8 == 0
        }.forEach { forecast ->
            GlassBox {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(16.dp)
                ) {
                    Text(
                        text = GeneralHelpers.formatDate(forecast.dt_txt.substring(0, 10)),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp
                    )
                    Image(
                        painter = painterResource(id = WeatherIcons.getIcon(forecast.weather.first().description)),
                        contentDescription = "Weather icon",
                        Modifier.size(30.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Text(
                            text = "${forecast.main.temp_min.roundToInt()}°",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 24.sp
                        )
                        Text(
                            text = "${forecast.main.temp_max.roundToInt()}°",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 24.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}