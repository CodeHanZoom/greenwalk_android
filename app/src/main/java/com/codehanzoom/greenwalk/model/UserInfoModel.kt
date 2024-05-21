package com.codehanzoom.greenwalk.model

data class UserInfoResponseBody(
    val id: Int,
    val name: String,
    val email: String,
    val totalPoint: Int,
    val totalDonation: Int,
    val totalStep: Int,
    val totalTrashCount: Int,
    val totalWalkingDistance: Double,
    val accumulatedPoint: Int
)

