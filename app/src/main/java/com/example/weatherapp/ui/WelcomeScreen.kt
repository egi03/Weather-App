package com.example.weatherapp.ui

import android.content.res.Configuration
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.data.WeatherAppViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    viewModel: WeatherAppViewModel = viewModel()
) {
    var searchedCity by remember { mutableStateOf(TextFieldValue("")) }
    val previousSearches = remember { mutableStateListOf("New York", "London", "Tokyo", "New York", "London", "Tokyo", "New York", "London", "Tokyo") } // Mock data

    // Get current time to determine greeting
    val currentHour = remember { Calendar.getInstance().get(Calendar.HOUR_OF_DAY) }
    val greeting = when(currentHour){
        in 5..11 -> "Good morning!"
        in 12..18 -> "Good afternoon!"
        else -> "Good night!"
    }

    val background = if (isSystemInDarkTheme()) R.drawable.background_dark else R.drawable.background_light

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = background),
            contentDescription = "Gradient background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = greeting,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Corrected OutlinedTextField
            OutlinedTextField(
                value = searchedCity,
                onValueChange = { searchedCity = it },
                label = {
                    Text(
                        text = "Enter City",
                        style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,))
                        },
                singleLine = true,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (searchedCity.text.isNotEmpty()) {
                        previousSearches.add(searchedCity.text)
                        searchedCity = TextFieldValue("")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Search",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Previous Searches",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.height(300.dp)
            ){
                items(previousSearches) { city ->
                    LocationCard(city)
                }
            }
        }
    }
}

@Composable
fun LocationCard(text: String){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color  = Color(0x33000000),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)

    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp
        )
        Icon(
            Icons.Rounded.Star,
            contentDescription = "Weather"
        )

    }
    Spacer(modifier = Modifier.height(16.dp))

}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewWelcomeScreen(){
    WeatherAppTheme {
        WelcomeScreen()
    }
}