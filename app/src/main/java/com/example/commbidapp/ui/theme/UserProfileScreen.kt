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
import coil3.compose.AsyncImage
import com.example.commbidapp.R




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    selectedImageUri: Uri?,
    onProfileImageClick: () -> Unit
) {
    // Zmienna dla Nickname
    var nickname by remember { mutableStateOf("TwójNick") }
    var isEditingNickname by remember { mutableStateOf(false) }

    // Zmienna dla opisu użytkownika
    var description by remember { mutableStateOf("Opis użytkownika...") }
    var isEditingDescription by remember { mutableStateOf(false) }

    // Zmienna dla przełącznika Artist Switch
    var isArtistSwitchChecked by remember { mutableStateOf(false) }

    // Użycie MutableState<Boolean>
    var isMenuOpen by remember { mutableStateOf(false) }

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
                        IconButton(onClick = { isMenuOpen = true }) { // Otwórz menu
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

                // Dodaj rozwijane menu
                DropdownMenu(
                    expanded = isMenuOpen,
                    onDismissRequest = { isMenuOpen = false } // Zamykanie menu po kliknięciu na tło
                ) {
                    DropdownMenuItem(
                        text = { Text("Zmień język") },  // Dodaj tekst
                        onClick = {
                            // Obsługa zmiany języka
                            isMenuOpen = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Wyjdź") },  // Dodaj tekst
                        onClick = {
                            // Obsługa wyjścia
                            isMenuOpen = false
                        }
                    )
                }
            }
        },
        bottomBar = {
            Column {
                Divider(color = Color.Gray, thickness = 1.dp)  // Ciemna linia nad dolnym paskiem
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

                // Ikona "+" pojawiająca się nad dolnym paskiem, gdy "Artist Switch" jest włączony
//                if (isArtistSwitchChecked) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(end = 16.dp),
//                        contentAlignment = Alignment.BottomEnd
//                    ) {
//                        IconButton(onClick = { /* Obsługa dodawania pracy */ }) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.plus_icon),
//                                contentDescription = "Add Work",
//                                tint = Color.Black,
//                                modifier = Modifier.size(48.dp)  // Ikona "+"
//                            )
//                        }
//                    }
//                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Profil użytkownika
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
                    if (isEditingNickname) {
                        OutlinedTextField(
                            value = nickname,
                            onValueChange = { newNickname -> nickname = newNickname },
                            label = { Text("Nickname") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        Text(
                            text = nickname,
                            fontSize = 22.sp,
                            color = Color.Black
                        )
                    }

                    Text(
                        text = "@uzytkownik123",
                        color = Color.Gray
                    )
                }

                IconButton(onClick = {
                    isEditingNickname = !isEditingNickname
                }) {
                    if (isEditingNickname) {
                        Icon(
                            painter = painterResource(id = R.drawable.save_pic),
                            contentDescription = "Save Nickname"
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.edit_pic),
                            contentDescription = "Edit Nickname"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sekcja opisu użytkownika
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                if (isEditingDescription) {
                    OutlinedTextField(
                        value = description,
                        onValueChange = { newDescription -> description = newDescription },
                        label = { Text("Description") },
                        modifier = Modifier
                            .weight(1f)
                            .height(150.dp)
                    )
                } else {
                    Text(
                        text = description,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 16.dp)
                    )
                }

                IconButton(onClick = {
                    isEditingDescription = !isEditingDescription
                }) {
                    if (isEditingDescription) {
                        Icon(
                            painter = painterResource(id = R.drawable.save_pic),
                            contentDescription = "Save Description"
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.edit_pic),
                            contentDescription = "Edit Description"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sekcja przełącznika Artist Switch
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Switch(
                    checked = isArtistSwitchChecked,
                    onCheckedChange = { isArtistSwitchChecked = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Black,
                        uncheckedThumbColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Artist Switch",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tutaj dodajemy ikonę "+" gdy przełącznik jest włączony
            if (isArtistSwitchChecked) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp, bottom = 16.dp),
                    contentAlignment = Alignment.BottomEnd  // Wyrównanie do prawego dolnego rogu
                ) {
                    IconButton(onClick = { /* Obsługa dodawania pracy */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = "Add Work",
                            tint = Color.Black,
                            modifier = Modifier.size(48.dp)  // Ikona "+"
                        )
                    }
                }
            }
        }
    }
}


