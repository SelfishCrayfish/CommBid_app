package com.example.commbidapp

data class LoginRequest(
    val username: String,
    val passwordHash: String
)
