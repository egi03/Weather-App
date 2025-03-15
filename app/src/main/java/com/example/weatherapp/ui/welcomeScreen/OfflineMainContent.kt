package com.example.weatherapp.ui.welcomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavController
import com.example.weatherapp.ViewModels.WelcomeScreenViewModel

@Composable
fun OfflineMainContent(
    previousSearches: List<String>,
    navController: NavController,
    viewModel: WelcomeScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You are offline",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color(0x74FFFFFF),
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.padding(20.dp))
        AppHeader()
        PreviousSearchesSection(
            previousSearches = previousSearches,
            navController = navController,
            viewModel = viewModel,
            isOnline = false
        )
    }
}