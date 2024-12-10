package com.example.commbidapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/auth/register")
    fun createUser(@Body user: User): Call<User>

    @POST("/api/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<String>
}
