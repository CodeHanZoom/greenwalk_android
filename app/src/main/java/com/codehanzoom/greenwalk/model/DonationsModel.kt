package com.codehanzoom.greenwalk.model

import com.google.gson.annotations.SerializedName

data class DonationsRequestBody(
    @SerializedName("partnerId") val partnerId: Int,
    @SerializedName("donationMoney") val donationMoney: Int
)
