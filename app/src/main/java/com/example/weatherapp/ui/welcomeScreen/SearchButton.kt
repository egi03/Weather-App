package com.example.weatherapp.ui.welcomeScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchButton(
    isLoading: Boolean,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x6FFFFFFF),
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = Color(0x3FFFFFFF),
            disabledContentColor = Color(0x4D000000)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(36.dp),
                color = MaterialTheme.colorScheme.scrim
            )
        } else {
            Text(
                text = "Search",
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000)
            )
        }
    }
}