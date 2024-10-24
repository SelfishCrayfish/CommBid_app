package com.example.commbidapp.ui.theme

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Sekcja profilu
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Zdjęcie profilowe z możliwością zmiany, przycinane do okręgu
            AsyncImage(
                model = selectedImageUri ?: R.drawable.profile_pic, // Domyślne zdjęcie profilowe
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
                    .clickable { onProfileImageClick() } // Kliknięcie na zdjęcie profilowe
                    .clip(CircleShape),  // Przycina do okręgu
                contentScale = ContentScale.FillBounds // Wypełnia cały obszar
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Nickname - edytowalny tekst
                if (isEditingNickname) {
                    OutlinedTextField(
                        value = nickname,
                        onValueChange = { newNickname -> nickname = newNickname },
                        label = { Text("Nickname") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                } else {
                    Text(
                        text = nickname,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }

                // Username (nieedytowalny)
                Text(
                    text = "@uzytkownik123",  // Zmień na odpowiedni username
                    color = Color.Gray
                )
            }

            // Ikona edycji/zapisu dla Nickname
            IconButton(onClick = {
                isEditingNickname = !isEditingNickname
            }) {
                if (isEditingNickname) {
                    Icon(
                        painter = painterResource(id = R.drawable.save_pic),  // Ikona dyskietki
                        contentDescription = "Save Nickname"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_pic),  // Ikona ołówka
                        contentDescription = "Edit Nickname"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sekcja opisu użytkownika
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Opis użytkownika - edytowalny tekst
            if (isEditingDescription) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { newDescription -> description = newDescription },
                    label = { Text("Description") },
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
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

            // Ikona edycji/zapisu dla opisu
            IconButton(onClick = {
                isEditingDescription = !isEditingDescription  // Zmieniaj stan edycji
            }) {
                if (isEditingDescription) {
                    Icon(
                        painter = painterResource(id = R.drawable.save_pic),  // Ikona dyskietki
                        contentDescription = "Save Description"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_pic),  // Ikona ołówka
                        contentDescription = "Edit Description"
                    )
                }
            }
        }
    }
}
