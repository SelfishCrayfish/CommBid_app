package com.example.commbidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.rememberScrollState

class OrderCommission : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrderCommissionScreen()
        }
    }
}

@Composable
fun OrderCommissionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Nagłówek
        Text(
            text = "List of your commissions",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista z placeholderami
        repeat(10) { index ->
            CommissionItem(title = "Commission #${index + 1}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CommissionItem(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White, // Białe tło
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Black, // Czarna cienka ramka
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}
