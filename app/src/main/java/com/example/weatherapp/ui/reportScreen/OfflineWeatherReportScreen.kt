
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.WeatherResponse
import com.example.weatherapp.ui.reportScreen.DailyForecast
import com.example.weatherapp.ui.reportScreen.GlassBox
import com.example.weatherapp.ui.reportScreen.HourlyForecast
import com.example.weatherapp.ui.reportScreen.ReturnButton
import com.example.weatherapp.ui.reportScreen.TemperatureSection
import com.example.weatherapp.ui.reportScreen.WeatherInfoGrid
import com.example.weatherapp.utils.GeneralHelpers
import com.example.weatherapp.utils.WeatherIcons


@Composable
fun OfflineWeatherReportScreen(
    navController: NavController
) {
    val weatherData = navController.previousBackStackEntry?.savedStateHandle?.get<WeatherResponse>("weatherData")
    val forecastData = navController.previousBackStackEntry?.savedStateHandle?.get<ForecastResponse>("forecastData")
    val dateTime = navController.previousBackStackEntry?.savedStateHandle?.get<String>("dateTime")

    if(weatherData != null && forecastData != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            BackgroundSection()


            ContentSection(weatherData, forecastData, navController, dateTime)
        }
    }
}

@Composable
fun BackgroundSection() {
    val backgroundRes = if (isSystemInDarkTheme()) R.drawable.background_dark else R.drawable.background_light
    Image(
        painter = painterResource(id = backgroundRes),
        contentDescription = "Background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ContentSection(weatherData: WeatherResponse?,
                   forecastData: ForecastResponse?,
                   navController: NavController,
                   dateTime: String?) {
    // Lazy column to make the content scrollable
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .padding(top=30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {

        item {

            Text(
                text = "Offline information",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0x60FFFFFF),
                    fontSize = 32.sp
                ),
                modifier = Modifier.padding(16.dp)
            )

        }

        item {

                Text(
                    text = "Last updated: ${GeneralHelpers.formatDateTimeFromUnix(dateTime?.toInt() ?: 0)}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Thin,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.padding(16.dp)
                )

        }

        item{
            GlassBox()
            {
                Text(
                    text = weatherData?.name ?: "Unknown Location",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Normal,
                        fontSize = 32.sp
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        item {
            Image(
                painterResource(id = WeatherIcons.getIcon(weatherData?.weather?.firstOrNull()?.description.orEmpty())),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(150.dp)
            )
        }

        item{
            TemperatureSection(weatherData)
        }

        if(forecastData != null){
            item{
                HourlyForecast(forecastData)
            }

            item{
                DailyForecast(forecastData)
            }
        }

        item{
            WeatherInfoGrid(weatherData)
        }

        item{
            ReturnButton(navController = navController)
        }

        item {

            Text(
                text = "Offline information",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0x60FFFFFF),
                    fontSize = 32.sp
                ),
                modifier = Modifier.padding(16.dp)
            )

        }

    }
}