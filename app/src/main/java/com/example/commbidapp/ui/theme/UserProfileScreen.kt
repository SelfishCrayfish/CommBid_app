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
import androidx.compose.ui.res.stringResource
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
    var nickname by remember { mutableStateOf("TwójNick") }
    var isEditingNickname by remember { mutableStateOf(false) }

    var description by remember { mutableStateOf("Opis użytkownika...") }
    var isEditingDescription by remember { mutableStateOf(false) }

    var isArtistSwitchChecked by remember { mutableStateOf(false) }

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
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.lupa_narazie),
                                contentDescription = "Search",
                                tint = Color.Black,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { isMenuOpen = true }) {
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

                DropdownMenu(
                    expanded = isMenuOpen,
                    onDismissRequest = { isMenuOpen = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.change_language)) },
                        onClick = {
                            isMenuOpen = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.exit)) },
                        onClick = {
                            isMenuOpen = false
                        }
                    )
                }
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
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "Icon 1",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Icon 2",
                            tint = Color.Black,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { }) {
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

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    if (isEditingNickname) {
                        OutlinedTextField(
                            value = nickname,
                            onValueChange = { newNickname -> nickname = newNickname },
                            label = { Text(stringResource(id = R.string.nickname )) },
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
                        text = stringResource(id = R.string.default_username),
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                if (isEditingDescription) {
                    OutlinedTextField(
                        value = description,
                        onValueChange = { newDescription -> description = newDescription },
                        label = { Text(stringResource(id = R.string.description)) },
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
                    text = stringResource(id = R.string.artist_switch),
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isArtistSwitchChecked) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp, bottom = 16.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus_icon),
                            contentDescription = "Add Work",
                            tint = Color.Black,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
    }
}


