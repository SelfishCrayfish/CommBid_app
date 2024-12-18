package com.example.commbidapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.PostScreen
import com.example.commbidapp.ui.theme.SplashScreen

class PostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostScreen(
                post = Post(
                    title = "title",
                    body = "body",
                    image = "https://i.imgur.com/6PC4d8g.jpeg",
                    user = User(
                        id = 0,
                        username = "username",
                        email = "email",
                        passwordHash = "pwdhash",
                        profilePicture = "https://i.imgur.com/6PC4d8g.jpeg",
                        artist = false,
                        openForCommissions = false,
                        lowestPrice = 0.0,
                        highestPrice = 1.0,
                        createdAt = "01.01.1970",
                        ratingsReceived = emptyList(),
                        about="",

                    ),
                ), comments = emptyList()
            )
        }
    }
}