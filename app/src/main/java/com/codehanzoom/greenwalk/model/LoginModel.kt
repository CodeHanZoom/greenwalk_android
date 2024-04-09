package com.codehanzoom.greenwalk.model

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    val email: String,
    val password: String
)

data class LoginResponseBody(
    @SerializedName("status")
    val status: Int?,
    @SerializedName("data")
    val data: String?,
)