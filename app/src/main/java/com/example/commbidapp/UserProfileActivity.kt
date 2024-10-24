package com.example.commbidapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.commbidapp.ui.theme.UserProfileScreen

class UserProfileActivity : ComponentActivity() {

    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri.value = it
        }
    }

    private val selectedImageUri = mutableStateOf<Uri?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserProfileScreen(
                selectedImageUri = selectedImageUri.value,
                onProfileImageClick = { selectImageLauncher.launch("image/*") }
            )
        }
    }
}
