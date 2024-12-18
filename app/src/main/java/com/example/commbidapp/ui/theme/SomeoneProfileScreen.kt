package com.example.commbidapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commbidapp.R
import com.example.commbidapp.UserProfileViewModel
import com.example.commbidapp.WallViewModel
import com.example.commbidapp.decodeImageUrlToImageBitmap

@Composable
fun SomeoneProfileScreen(
    onCommissionButtonClick: () -> Unit,
    userId: Int,
    wallViewModel: WallViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    userProfileViewModel: UserProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val user by userProfileViewModel.user.observeAsState()
    val isUserLoading by userProfileViewModel.isLoading.observeAsState(true)
    val context = LocalContext.current

    LaunchedEffect(userId) {
        userProfileViewModel.fetchUserById(userId)
        wallViewModel.fetchPosts(userId)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (isUserLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            user?.let { userData ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val profilePictureBitmap by produceState<ImageBitmap?>(initialValue = null) {
                        value = decodeImageUrlToImageBitmap(
                            userData.profilePicture ?: "https://i.imgur.com/6PC4d8g.jpeg"
                        )
                    }

                    profilePictureBitmap?.let {
                        Image(
                            painter = BitmapPainter(it),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = userData.username,
                            fontSize = 22.sp,
                            color = Color.Black
                        )
                        Text(
                            text = userData.about ?: "No description available.",
                            color = Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomButton(
                    text = stringResource(id = R.string.commission),
                    onClick = { onCommissionButtonClick() },
                    backgroundColor = CelestialBlueColor,
                    fontFamily = RegularFont,
                    modifier = Modifier.width(180.dp)
                )
                CustomButton(
                    text = stringResource(id = R.string.follow),
                    onClick = { /* Follow Action */ },
                    backgroundColor = CelestialBlueColor,
                    fontFamily = RegularFont,
                    modifier = Modifier.width(180.dp)
                )
            }

            // Display posts using WallScreen
            WallScreen(viewModel = wallViewModel, userId = userId)
        }
    }
}
