package com.example.weatherapp.ui.welcomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Composable
fun AppHeader() {
    Text(
        text = "WeatherApp",
        style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 48.sp,
            fontWeight = FontWeight.Thin
        ),
        modifier = Modifier.padding(bottom = 24.dp)
    )
    Image(
        painter = painterResource(R.drawable.ic_weather),
        contentDescription = "logo",
        modifier = Modifier.size(150.dp)
    )
}