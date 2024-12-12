package com.example.commbidapp

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PostService {
    @GET("/api/posts")
    suspend  fun getAllPosts(): List<Post>

    @GET("api/posts/user/{id}")
    suspend fun getPostbyUser(@Path("id") id: Int): List<Post>

    @POST("/api/posts")
    @Headers("Content-Type: application/json")
    fun createPost(
        @Body post: Post
    ): Call<Post>
}


data class Post(
    val title: String,
    val body: String,
    val image: String,
    val user: User
)


