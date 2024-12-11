package com.example.commbidapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.CreatePostScreen
import com.example.commbidapp.ui.theme.PagerScreen

class CreatePostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreatePostScreen(
                onPostAdded = { postContent, imageUri ->
                    val message = "Tutaj chyba jakas backendowa logika"
                    setContent {
                        PagerScreen(pageNumber = 1)
                    }
                },
                onCancel = {
                    setContent {
                        PagerScreen(pageNumber = 1)
                    }
                }
            )
        }
    }
}
