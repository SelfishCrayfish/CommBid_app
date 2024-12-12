package com.example.commbidapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val profilePicture: String?,
    val isArtist: Boolean,
    val openForCommissions: Boolean,
    val lowestPrice: Double?,
    val highestPrice: Double?,
    val createdAt: String,
    val jwt: String
)


interface UserService {
    @POST("/api/auth/register")
    fun createUser(@Body user: User): Call<User>

    @POST("/api/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
