package com.example.commbidapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.RegisterScreen

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen(onRegisterSuccess = { navigateToUserProfile() })
        }
    }

    private fun navigateToUserProfile() {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
    }
}
