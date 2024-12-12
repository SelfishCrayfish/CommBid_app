package com.example.commbidapp

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


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
    val about: String?,
    val jwt: String
)

data class DescriptionRequest(val about: String)

data class PfpRequest(val profilePicture: String)

data class usernameRequest(val username: String)

data class ArtistRequest(val artist: Boolean)

interface UserService {
    @POST("/api/auth/register")
    fun createUser(@Body user: User): Call<User>

    @POST("/api/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @PUT("/api/users/{id}")
    fun changeDescription(
        @Path("id") userId: Int,
        @Body descriptionRequest: DescriptionRequest
    ) : Call<ResponseBody>

    @PUT("/api/users/{id}")
    fun changePfp(
        @Path("id") userId: Int,
        @Body pfpRequest: PfpRequest
    ) : Call<ResponseBody>

    @PUT("/api/users/{id}")
    fun changeUsername(
        @Path("id") userId: Int,
        @Body usernameRequest: usernameRequest
    ) : Call<ResponseBody>

    @PUT("/api/users/{id}")
    fun changeIsArtist(
        @Path("id") userId: Int,
        @Body artistRequest: ArtistRequest
    ) : Call<ResponseBody>
}
