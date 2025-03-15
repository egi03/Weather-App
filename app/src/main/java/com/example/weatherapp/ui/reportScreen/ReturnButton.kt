package com.example.weatherapp.ui.reportScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ReturnButton(navController: NavController){
    Box(
        modifier = Modifier
            .padding(top=30.dp)
            .padding(bottom=30.dp)
            .clickable(onClick = { navController.popBackStack() })
    ){
        GlassBox {
            Text(
                text = "Return",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Normal,
                    fontSize = 32.sp

                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}
