package com.example.commbidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.MainScreenWithNavbar
import com.example.commbidapp.ui.theme.UserProfileScreen
import com.example.commbidapp.ui.theme.WallScreen

class WallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenWithNavbar {
                WallScreen()
            }
        }
    }
}
