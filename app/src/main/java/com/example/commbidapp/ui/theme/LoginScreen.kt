package com.example.commbidapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import com.example.commbidapp.R


@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val screenWidth = maxWidth
            val screenHeight = maxHeight
            val isLandscape = screenWidth > screenHeight
            val isTablet = (screenWidth > 600.dp && screenHeight > 900.dp) ||
                    (screenWidth > 900.dp && screenHeight > 600.dp)

            if (isTablet) {
                TabletLayout(isLandscape, onLoginSuccess)
            } else {
                PhoneLayout(isLandscape, onLoginSuccess)
            }
        }
    }
}

@Composable
fun TabletLayout(isLandscape: Boolean, onLoginSuccess: () -> Unit) {
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
            modifier = Modifier
                .size(if (isLandscape) 300.dp else 350.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(if (isLandscape) 0.dp else 100.dp))

        // Add TextFields for Email and Password
        var email by remember { mutableStateOf(TextFieldValue()) }
        var password by remember { mutableStateOf(TextFieldValue()) }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Ikony Facebook i Instagram
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
            text = "Proceed",
            onClick = {
                onLoginSuccess()
            },
            backgroundColor = BlueCrayolaColor,
            fontFamily = RegularFont,
            modifier = Modifier.width(250.dp)
        )
    }
}

@Composable
fun PhoneLayout(isLandscape: Boolean, onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_commbid),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(if (isLandscape) 200.dp else 300.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(if (isLandscape) 0.dp else 100.dp))

        // Add TextFields for Email and Password
        var email by remember { mutableStateOf(TextFieldValue()) }
        var password by remember { mutableStateOf(TextFieldValue()) }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Ikony Facebook i Instagram
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
            text = "Proceed",
            onClick = {
                onLoginSuccess()
            },
            fontFamily = RegularFont,
            backgroundColor = BlueCrayolaColor
        )
    }
}