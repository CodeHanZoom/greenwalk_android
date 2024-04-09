package com.codehanzoom.greenwalk.model

import com.google.gson.annotations.SerializedName

data class SignUpRequestBody(
    val email: String?,
    val password: String?,
    val name: String?
)

data class SignUpResponseBody(
    @SerializedName("status")
    val status: Int?,
    @SerializedName("data")
    val data: String?
)
