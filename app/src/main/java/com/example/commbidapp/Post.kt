package com.example.commbidapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostService {
    @GET("/api/posts")
    suspend  fun getAllPosts(): List<Post>
}


data class Post(
    val title: String,
    val body: String,
    val image: String,
    val user: User
)

