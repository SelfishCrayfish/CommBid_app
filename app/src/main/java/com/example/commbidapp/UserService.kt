package com.example.commbidapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/users")
    fun createUser(@Body user: User): Call<User>
}
