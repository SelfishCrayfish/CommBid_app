package com.example.commbidapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import com.example.commbidapp.ui.theme.SomeoneProfileScreen

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
                onCommissionButtonClick = { navigateToCommission() },
                selectedImageUri = selectedImageUri.value,
            ) { selectImageLauncher.launch("image/*") }
        }
    }

    private fun navigateToCommission() {
        val intent = Intent(this, CommissionActivity::class.java)
        startActivity(intent)
    }
}
