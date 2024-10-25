package com.example.commbidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.RegisterScreen
import java.util.regex.Pattern

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RegisterScreen (onRegisterSuccess = { email, password, confirmPassword ->
                val emailError = getEmailValidationError(email)
                val passwordError = getPasswordValidationError(password)
                val confirmPasswordError = getConfirmPasswordValidationError(password, confirmPassword)

                when {
                    emailError != null -> {
                        Toast.makeText(this, emailError, Toast.LENGTH_LONG).show()
                    }

                    passwordError != null -> {
                        Toast.makeText(this, passwordError, Toast.LENGTH_LONG).show()
                    }

                    confirmPasswordError != null -> {
                        Toast.makeText(this, confirmPasswordError, Toast.LENGTH_LONG).show()
                    }

                    else -> {
                        navigateToUserProfile()
                    }
                }
            })
        }
    }

    private fun navigateToUserProfile() {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
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

    private fun getConfirmPasswordValidationError(password: String, confirmPassword: String): String? {
        return if (password != confirmPassword) {
            "Passwords do not match."
        } else {
            null
        }
    }
}
