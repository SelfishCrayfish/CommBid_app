package com.example.commbidapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import com.example.commbidapp.R

@Composable
fun LoginScreen(onLoginSuccess: (String, String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        DiagonalStripesBackground(
            color1 = SunglowColor,
            color2 = CarrotOrangeColor,
            stripeWidth = 15f,
            spacing = 60f
        )
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val screenWidth = maxWidth
            val screenHeight = maxHeight
            val isLandscape = screenWidth > screenHeight
            val isTablet = (screenWidth > 600.dp && screenHeight > 900.dp) ||
                    (screenWidth > 900.dp && screenHeight > 600.dp)

            if (isTablet) {
                LoginTabletLayout(isLandscape, onLoginSuccess)
            } else {
                LoginPhoneLayout(isLandscape, onLoginSuccess)
            }
        }
    }
}

@Composable
fun LoginTabletLayout(isLandscape: Boolean, onLoginSuccess: (String, String) -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_commbid),
            contentDescription = "App Logo",
            modifier = Modifier.size(if (isLandscape) 300.dp else 350.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(if (isLandscape) 0.dp else 100.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                if (it.text.length <= 50) {
                    email = it
                }
            },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.width(400.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Gray,
                errorContainerColor = Color.Red,
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                if (it.text.length <= 20) {
                    password = it
                }
            },
            label = { Text(stringResource(id = R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.width(400.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.Gray,
                errorContainerColor = Color.Red,
            ),
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.width(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fb_icon),
                contentDescription = "Facebook",
                modifier = Modifier.size(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ig_icon),
                contentDescription = "Instagram",
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        CustomButton(
            text = stringResource(id = R.string.proceed),
            onClick = {
                onLoginSuccess(email.text, password.text)
            },
            backgroundColor = BlueCrayolaColor,
            fontFamily = RegularFont,
            modifier = Modifier.width(250.dp)
        )
    }
}

@Composable
fun LoginPhoneLayout(isLandscape: Boolean, onLoginSuccess: (String, String) -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_commbid),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(1f)
                    .size(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(32.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        if (it.text.length <= 50) {
                            email = it
                        }
                    },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    modifier = Modifier.width(400.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        errorContainerColor = Color.Red,
                    ),
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        if (it.text.length <= 20) {
                            password = it
                        }
                    },
                    label = { Text(stringResource(id = R.string.password)) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.width(400.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        errorContainerColor = Color.Red,
                    ),
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fb_icon),
                        contentDescription = "Facebook",
                        modifier = Modifier.size(40.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ig_icon),
                        contentDescription = "Instagram",
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                CustomButton(
                    text = stringResource(id = R.string.proceed),
                    onClick = {
                        onLoginSuccess(email.text, password.text)
                    },
                    fontFamily = RegularFont,
                    backgroundColor = BlueCrayolaColor,
                    modifier = Modifier.width(250.dp)
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_commbid),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(300.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(100.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    if (it.text.length <= 50) {
                        email = it
                    }
                },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                modifier = Modifier.width(400.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    errorContainerColor = Color.Red,
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    if (it.text.length <= 20) {
                        password = it
                    }
                },
                label = { Text(stringResource(id = R.string.password)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.width(400.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    errorContainerColor = Color.Red,
                ),
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fb_icon),
                    contentDescription = "Facebook",
                    modifier = Modifier.size(40.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ig_icon),
                    contentDescription = "Instagram",
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            CustomButton(
                text = stringResource(id = R.string.proceed),
                onClick = {
                    onLoginSuccess(email.text, password.text)
                },
                fontFamily = RegularFont,
                backgroundColor = BlueCrayolaColor,
                modifier = Modifier.width(250.dp)
            )
        }
    }
}
