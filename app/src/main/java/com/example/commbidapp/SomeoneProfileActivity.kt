package com.example.commbidapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.commbidapp.ui.theme.SomeoneProfileScreen
import com.example.commbidapp.ui.theme.CommissionScreen

class SomeoneProfileActivity : ComponentActivity() {

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
            SomeoneProfileScreen(
                selectedImageUri = selectedImageUri.value,
                onProfileImageClick = { selectImageLauncher.launch("image/*") },
                onButtonClick = {  // Dodanie obsługi przekierowania po kliknięciu przycisku
                    val intent = Intent(this@SomeoneProfileActivity, CommissionActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}
