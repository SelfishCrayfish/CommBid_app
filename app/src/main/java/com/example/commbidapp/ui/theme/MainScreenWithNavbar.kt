package com.example.commbidapp.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.commbidapp.ui.theme.TopNavBar

@Composable
fun MainScreenWithNavbar(
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopNavBar(title = "", onMenuClick = {
                // Możesz tu dodać logikę, np. otwarcie menu bocznego
                println("Burger menu clicked")
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            content()
        }
    }
}
