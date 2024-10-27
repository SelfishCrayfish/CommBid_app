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
import com.example.commbidapp.R
import coil3.compose.AsyncImage

@Composable
fun SomeoneProfileScreen(
    onCommissionButtonClick: () -> Unit,
    selectedImageUri: Uri?,
    onProfileImageClick: () -> Unit
) {
    var nickname by remember { mutableStateOf("ArtystaNick") }

    var description by remember { mutableStateOf("Opis artysty...") }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                Text(
                    text = nickname,
                    fontSize = 22.sp,
                    color = Color.Black
                )

                Text(
                    text = stringResource(id = R.string.default_username),
                    color = Color.Gray
                )
            }

            CustomButton(
                text = stringResource(id = R.string.follow),
                onClick = { },
                modifier = Modifier.width(170.dp),
                backgroundColor = CelestialBlueColor,
                fontFamily = RegularFont
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(end = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(
                text = stringResource(id = R.string.portfolio),
                onClick = { },
                backgroundColor = CelestialBlueColor,
                fontFamily = RegularFont
            )

            CustomButton(
                text = stringResource(id = R.string.commission),
                onClick = { onCommissionButtonClick() },
                backgroundColor = CelestialBlueColor,
                fontFamily = RegularFont
            )
        }
    }
}