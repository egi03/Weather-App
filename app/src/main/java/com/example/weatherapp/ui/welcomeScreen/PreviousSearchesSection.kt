package com.example.weatherapp.ui.welcomeScreen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.ViewModels.WelcomeScreenViewModel

@Composable
fun PreviousSearchesSection(
    previousSearches: List<String>,
    navController: NavController,
    viewModel: WelcomeScreenViewModel
) {
    Text(
        text = "Previous Searches",
        style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    )
    LazyColumn(modifier = Modifier.height(300.dp)) {
        items(previousSearches) { location ->
            LocationCard(location, navController, viewModel)
        }
    }
}
