package com.example.commbidapp.ui.theme

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.commbidapp.R
import java.util.*

object AppState {
    var currentLocale by mutableStateOf(Locale.getDefault())
        private set
    fun updateLocale(locale: Locale) {
        currentLocale = locale
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(onLanguageChanged: () -> Unit) {
    val c = LocalContext.current
    val context by remember { mutableStateOf(c) }
    val currentLocale = AppState.currentLocale

    var isLanguageDialogOpen by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo_commbid),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .width(104.dp)
                        .height(64.dp)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        actions = {
            Box {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(id = R.string.change_language)) },
                        onClick = {
                            expanded = false
                            isLanguageDialogOpen = true
                        }
                    )
                }
            }
        }
    )

    if (isLanguageDialogOpen) {
        AlertDialog(
            onDismissRequest = { isLanguageDialogOpen = false },
            title = { Text(stringResource(id = R.string.select_language)) },
            text = {
                Column {
                    Text(
                        text = "English",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                setLocale(context, "en")
                                isLanguageDialogOpen = false
                                onLanguageChanged()
                            }
                            .padding(8.dp),
                        style = TextStyle(fontSize = 25.sp, fontFamily = FontFamily.Default)
                    )
                    Text(
                        text = "Polski",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                setLocale(context, "pl")
                                isLanguageDialogOpen = false
                                onLanguageChanged()
                            }
                            .padding(8.dp),
                        style = TextStyle(fontSize = 25.sp, fontFamily = FontFamily.Default)
                    )
                }
            },
            confirmButton = {
                CustomButton(
                    text = stringResource(id = R.string.close),
                    onClick = { isLanguageDialogOpen = false },
                    fontFamily = RegularFont,
                    backgroundColor = BlueCrayolaColor,
                    modifier = Modifier.width(150.dp)
                )
            }
        )
    }

    SideEffect {
        context.resources.updateConfiguration(
            Configuration(context.resources.configuration).apply {
                setLocale(currentLocale)
            },
            context.resources.displayMetrics
        )
    }
}


fun setLocale(context: Context, language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    AppState.updateLocale(locale)
}
