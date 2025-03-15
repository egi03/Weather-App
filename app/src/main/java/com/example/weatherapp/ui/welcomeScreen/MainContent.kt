package com.example.weatherapp.ui.welcomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.ViewModels.WelcomeScreenViewModel

@Composable
fun MainContent(
    searchedCity: TextFieldValue,
    onSearchChange: (TextFieldValue) -> Unit,
    isLoading: Boolean,
    previousSearches: List<String>,
    onSearch: () -> Unit,
    navController: NavController,
    viewModel: WelcomeScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader()
        SearchSection(
            searchedCity = searchedCity,
            onSearchChange = onSearchChange,
            isLoading = isLoading,
            onSearch = onSearch
        )
        PreviousSearchesSection(
            previousSearches = previousSearches,
            navController = navController,
            viewModel = viewModel
        )
    }
}