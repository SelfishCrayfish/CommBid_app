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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.commbidapp.R

@Composable
fun WallPosts(posts: List<Post>) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts.size) { index ->
            PostItem(post = posts[index])
        }
    }
}

@Composable
fun PostItem(post: Post) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = post.profilePicture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = post.nickname, fontSize = 16.sp, color = Color.Black)
                Text(text = "@${post.username}", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = post.postImage),
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

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
            text = if (expanded) post.description else post.description.take(100),
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

data class Post(
    val profilePicture: Int,
    val nickname: String,
    val username: String,
    val postImage: Int,
    val description: String
)
