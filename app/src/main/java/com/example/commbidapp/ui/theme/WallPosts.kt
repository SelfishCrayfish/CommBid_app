package com.example.commbidapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commbidapp.Post
import com.example.commbidapp.R
import com.example.commbidapp.decodeImageUrlToImageBitmap
import kotlinx.coroutines.runBlocking

@Composable
fun WallPosts(posts: List<Post>, navigateToProfile: (String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts.size) { index ->
            PostItem(post = posts[index], navigateToProfile = navigateToProfile)
        }
    }
}

@Composable
fun PostItem(post: Post, navigateToProfile: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    // Profile picture loading
    val profilePictureBitmap by produceState<ImageBitmap?>(initialValue = null) {
        value = decodeImageUrlToImageBitmap("https://i.imgur.com/6PC4d8g.jpeg")
    }

    // Post image loading
    val postImageBitmap by produceState<ImageBitmap?>(initialValue = null) {
        value = decodeImageUrlToImageBitmap("https://i.imgur.com/6PC4d8g.jpeg")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            profilePictureBitmap?.let {
                Image(
                    painter = BitmapPainter(it),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                        .clickable { navigateToProfile(post.user.username) } // Kliknięcie na zdjęcie profilowe
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = post.user.username, fontSize = 16.sp, color = Color.Black)
                Text(text = "@${post.user.username}", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        postImageBitmap?.let {
            Image(
                painter = BitmapPainter(it),
                contentDescription = "Post Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { }) {
                Icon(painterResource(R.drawable.like_pic), contentDescription = "Reakcja")
            }
            IconButton(onClick = { }) {
                Icon(painterResource(R.drawable.comment_pic), contentDescription = "Komentarz")
            }
            IconButton(onClick = { }) {
                Icon(painterResource(R.drawable.handshake_pic), contentDescription = "Współpraca")
            }
            IconButton(onClick = { }) {
                Icon(painterResource(R.drawable.favourite), contentDescription = "Ulubione")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (expanded) post.body else post.body.take(100),
            fontSize = 14.sp,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = if (expanded) "Zobacz mniej..." else "Zobacz więcej...",
            color = Color.Blue,
            fontSize = 12.sp,
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(top = 4.dp)
        )
    }
}
