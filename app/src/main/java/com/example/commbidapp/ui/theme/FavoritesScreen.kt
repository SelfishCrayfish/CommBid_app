package com.example.commbidapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoritesScreen() {
    Column {
        Text(
            text = "JESTEM FAVORITES",
            modifier = Modifier.fillMaxSize()
        )
    }
}