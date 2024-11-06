package com.example.commbidapp

import com.example.commbidapp.ui.theme.PagerScreen
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.LoginScreen
import java.util.regex.Pattern

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen(onLoginSuccess = { email, password ->
                val emailError = getEmailValidationError(email)
                val passwordError = getPasswordValidationError(password)

                when {
//                    emailError != null -> {
//                        Toast.makeText(this, emailError, Toast.LENGTH_LONG).show()
//                    }

//                    passwordError != null -> {
//                        Toast.makeText(this, passwordError, Toast.LENGTH_LONG).show()
//                    }

                    else -> {
                        setContent {
                            PagerScreen()
                        }
                    }
                }
            })
        }
    }

    private fun getEmailValidationError(email: String): String? {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return if (!Pattern.matches(emailPattern, email)) {
            "Invalid email format. Please enter a valid email."
        } else {
            null
        }
    }

    private fun getPasswordValidationError(password: String): String? {
        val passwordPattern =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        return if (!Pattern.matches(passwordPattern, password)) {
            ("The password must have at least one upper case letter, " +
                    "one lower case letter, a number and a special character " +
                    "and must be at least 8 characters long").trimIndent()
        } else {
            null
        }
    }
}
