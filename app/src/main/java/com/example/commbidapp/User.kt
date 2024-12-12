package com.example.commbidapp

import java.math.BigDecimal
import java.sql.Timestamp



data class Rating(
    val score: Int,      // Rating score (e.g., 1 to 5)
    val comment: String  // Optional comment for the rating
)

data class User(
    val id : Int?,
    val username: String,
    val email: String,
    val passwordHash: String,
    val profilePicture: String?,
    val isArtist: Boolean,
    val openForCommissions: Boolean,
    val lowestPrice: Double?,
    val highestPrice: Double?,
    val createdAt: String,
    val about: String?,
    val ratingsReceived: List<Rating> // Added ratingsReceived field
)
