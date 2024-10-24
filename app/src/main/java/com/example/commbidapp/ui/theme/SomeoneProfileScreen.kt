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
fun SomeoneProfileScreen(
    selectedImageUri: Uri?,
    onProfileImageClick: () -> Unit,
    onButtonClick: () -> Unit
) {
    var nickname by remember { mutableStateOf("ArtystaNick") }
    var isEditingNickname by remember { mutableStateOf(false) }

    var description by remember { mutableStateOf("Opis artysty...") }
    var isEditingDescription by remember { mutableStateOf(false) }

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
                                modifier = Modifier.size(128.dp),
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
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Obsługa burger menu */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.burger_menu),
                                contentDescription = "Menu",
                                tint = Color.Black,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black
                    )
                )
                Divider(color = Color.Gray, thickness = 1.dp)
            }
        },
        bottomBar = {
            Column {
                Divider(color = Color.Gray, thickness = 1.dp)
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    containerColor = Color.White,
                    contentColor = Color.Black
                ) {
                    IconButton(onClick = { /* Obsługa lewej ikony */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "Icon 1",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* Obsługa środkowej ikony */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Icon 2",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* Obsługa prawej ikony */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.favourite),
                            contentDescription = "Icon 3",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = selectedImageUri ?: R.drawable.profile_pic,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(90.dp)
                        .clickable { onProfileImageClick() }
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )

                Spacer(modifier = Modifier.width(16.dp))  // Dodany margines pomiędzy zdjęciem a nickiem


                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = nickname,
                        fontSize = 22.sp,
                        color = Color.Black
                    )

                    Text(
                        text = "@artysta123",
                        color = Color.Gray
                    )
                }

                IconButton(onClick = { /* Follow button click */ }) {
                    Button(onClick = { /* Follow action */ }) {
                        Text("Follow")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(end = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Sekcja kontaktowa zamiast Artist Switch
            Text(text = "Email: artysta@example.com", color = Color.Black, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Instagram: @artysta_ig", color = Color.Black, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Pinterest: @artysta_pin", color = Color.Black, fontSize = 16.sp)

            Spacer(modifier = Modifier.weight(1f))

            // Dwa przyciski nad bottom bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Portfolio action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Portfolio", color = Color.White)
                }

                Button(
                    onClick = {onButtonClick()},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Commission", color = Color.White)
                }
            }
        }
    }
}

