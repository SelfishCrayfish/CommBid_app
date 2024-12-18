package com.example.commbidapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.SomeoneProfileScreen

class SomeoneProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId = intent.getIntExtra("userId", -1)

        setContent {
                SomeoneProfileScreen(onCommissionButtonClick = {
                    navigateToCommission()
                }
                    ,userId = userId
            )
        }
    }

    private fun navigateToCommission() {
        val intent = Intent(this, CommissionActivity::class.java)
        startActivity(intent)
    }
}
