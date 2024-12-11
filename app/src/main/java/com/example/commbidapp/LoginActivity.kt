package com.example.commbidapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.commbidapp.ui.theme.LoginScreen
import com.example.commbidapp.ui.theme.PagerScreen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.regex.Pattern

class LoginActivity : ComponentActivity() {

    private lateinit var userService: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://lit-sands-62255-e863c9112a48.herokuapp.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        userService = retrofit.create(UserService::class.java)

        setContent {
            LoginScreen(onLoginSuccess = { email, password ->
                //val emailError = getEmailValidationError(email)
                val passwordError = getPasswordValidationError(password)

                when {

                    passwordError != null -> {
                        Toast.makeText(this, passwordError, Toast.LENGTH_LONG).show()
                    }

                    else -> {
                        loginUser(email, password)
                    }
                }
            })
        }
    }

    private fun loginUser(username: String, password: String) {
        val loginRequest = LoginRequest(
            username = username,
            passwordHash = password
        )

        val call = RetrofitInstance.userService.login(loginRequest)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val jwtToken = response.body()
                    if (jwtToken != null) {
                        Toast.makeText(this@LoginActivity, "Login Successful!", Toast.LENGTH_SHORT).show()

                        // Store the JWT token if needed (e.g., in SharedPreferences)
                        saveJwtToken(jwtToken)

                        // Navigate to the next screen
                        setContent {
                            PagerScreen(pageNumber = 0)
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Failed to retrieve token!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveJwtToken(token: String) {
        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("JWT_TOKEN", token)
            .apply()
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
