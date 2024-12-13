package com.example.commbidapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.commbidapp.Post
import com.example.commbidapp.UserComment

@Composable
fun PostScreen(post: Post, comments: List<UserComment>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = post.title, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
        AsyncImage(
            model = post.image,
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = post.body, fontSize = 16.sp, modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(comments) { comment ->
                UserCommentItem(comment = comment)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun UserCommentItem(comment: UserComment) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        AsyncImage(
            model = comment.user.profilePicture,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(text = "@${comment.user.username}", fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = comment.text, fontSize = 14.sp)
        }
    }
}
