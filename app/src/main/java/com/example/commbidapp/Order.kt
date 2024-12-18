package com.example.commbidapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class Order (
    val user : User,
    val artist : User,
    val description : String,
    val price : Double,
    var status: String,
    val orderedAt: String?,
    val finishedAt: String?
    )

data class orderStatusChange(
    val status: String
)
interface OrderService
{
    @POST("/api/orders")
    @Headers("Content-Type: application/json")
    fun createOrder(
        @Body order: Order
    ): Call<Order>

    @GET("/api/orders/user/{clientId}")
    fun getClientOrders(
        @Path ("clientId") clientId : Int
    ): Call<List<Order>>

    @GET("/api/orders/artist/{artistId}")
    fun getArtistOrders(
        @Path ("artistId") artistId : Int
    ): Call<List<Order>>



    @PUT("/api/orders/{orderId}")
    fun updateOderStatus(
        @Path("orderId") orderId: Int,
        @Body orderStatusChange: orderStatusChange
    ) :Call<Order>

}
