package com.example.weatherapp.ui.welcomeScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    searchedCity: TextFieldValue,
    onSearchChange: (TextFieldValue) -> Unit,
    isLoading: Boolean,
    onSearch: () -> Unit
) {
    CityTextField(
        value = searchedCity,
        onValueChange = onSearchChange,
        onSearch = onSearch
    )
    Spacer(modifier = Modifier.height(16.dp))
    SearchButton(
        isLoading = isLoading,
        onClick = onSearch,
        enabled = searchedCity.text.isNotEmpty()
    )
    Spacer(modifier = Modifier.height(64.dp))
}