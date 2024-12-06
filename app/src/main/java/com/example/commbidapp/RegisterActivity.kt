package com.example.commbidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.RegisterScreen
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RegisterScreen(onRegisterSuccess = { username, email, password, confirmPassword ->
                val usernameError = getUsernameValidationError(username)
                val emailError = getEmailValidationError(email)
                val passwordError = getPasswordValidationError(password)
                val confirmPasswordError = getConfirmPasswordValidationError(password, confirmPassword)

                when {
                    usernameError != null -> {
                        Toast.makeText(this, usernameError, Toast.LENGTH_LONG).show()
                    }
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
                        val createdAt = ZonedDateTime.now()
                        val formattedDate = createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

                        // Convert ZonedDateTime to Instant (which is used by Timestamp)
                        val timestamp = formattedDate


                        val user = User(
                            username = email.split("@")[0], // Example username
                            email = email,
                            passwordHash = password, // Normally hash the password
                            profilePicture = null,
                            isArtist = false,
                            openForCommissions = false,
                            lowestPrice = null,
                            highestPrice = null,
                            createdAt = timestamp,

                            ratingsReceived = emptyList()
                        )

                        // Make network call to create the user
                        RetrofitInstance.api.createUser(user).enqueue(object : retrofit2.Callback<User> {
                            override fun onResponse(call: retrofit2.Call<User>, response: retrofit2.Response<User>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(this@RegisterActivity, "User registered successfully!", Toast.LENGTH_LONG).show()
                                    navigateToUserProfile()
                                } else {
                                    Toast.makeText(this@RegisterActivity, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                                }
                            }

                            override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
                                Toast.makeText(this@RegisterActivity, "Failed to connect: ${t.message}", Toast.LENGTH_LONG).show()
                            }
                        })
                    }
                }
            })
        }
    }

    private fun navigateToUserProfile() {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
    }

    private fun getUsernameValidationError(username: String): String? {
        val usernamePattern = "[ !@#%^&*()\\[\\]|\\\\;:'\",.<>/?~`\\-+=]"
        return if (!Pattern.matches(usernamePattern, username)) {
            "Invalid username format. Username cannot contain special characters like ! @ # % ^ & * ( ) [ ] | \\ ; : ' \" , . < > / ? ~ ` - + = ."
        } else {
            null
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

    private fun getConfirmPasswordValidationError(password: String, confirmPassword: String): String? {
        return if (password != confirmPassword) {
            "Passwords do not match."
        } else {
            null
        }
    }
}
