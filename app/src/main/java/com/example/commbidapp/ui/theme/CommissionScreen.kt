package com.example.commbidapp.ui.theme

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commbidapp.R
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommissionScreen(
    selectedImageUri: Uri?,
    onProfileImageClick: () -> Unit
) {
    // Zmienne do przechowywania tekstu wprowadzonych przez użytkownika
    var artStyle by remember { mutableStateOf("") }
    var artDescription by remember { mutableStateOf("") }
    var extraDetails by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.logo_commbid),
                                contentDescription = "Logo",
                                modifier = Modifier.size(128.dp),  // Powiększone logo
                                tint = Color.Unspecified
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Obsługa wyszukiwania */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.lupa_narazie),
                                contentDescription = "Search",
                                tint = Color.Black,
                                modifier = Modifier.size(32.dp)  // Powiększenie ikony
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Obsługa burger menu */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.burger_menu),
                                contentDescription = "Menu",
                                tint = Color.Black,
                                modifier = Modifier.size(32.dp)  // Powiększenie ikony
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black
                    )
                )
                Divider(color = Color.Gray, thickness = 1.dp)  // Ciemna linia oddzielająca pasek
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                IconButton(onClick = { /* Obsługa lewej ikony na dolnym pasku */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Icon 1",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Obsługa środkowej ikony na dolnym pasku */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Icon 2",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Obsługa prawej ikony na dolnym pasku */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.favourite),
                        contentDescription = "Icon 3",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Tekst i pola tekstowe
            Column {
                Text(
                    text = "Choose/explain desired artstyle:",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = artStyle,
                    onValueChange = { artStyle = it },
                    placeholder = { Text("e.g., watercolor, digital painting") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Describe in detail what exactly should the art represent:",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = artDescription,
                    onValueChange = { artDescription = it },
                    placeholder = { Text("Describe the scene, characters, etc.") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Give any extra details you'd like to be included:",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = extraDetails,
                    onValueChange = { extraDetails = it },
                    placeholder = { Text("e.g., color preferences, background") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )
            }

            // Przycisk nad dolnym paskiem
            Button(
                onClick = { /* Obsługa wyboru metody płatności */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Confirm and choose payment method")
            }
        }
    }
}
