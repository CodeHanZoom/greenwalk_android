package com.codehanzoom.greenwalk.api

import com.codehanzoom.greenwalk.model.DonationRecord
import com.codehanzoom.greenwalk.model.UserInfoResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("userInfo")
    fun getUserInfo(
        @Header("Authorization") accessToken: String
    ): Call<UserInfoResponseBody>

    @GET("users/donations")
    fun getDonationList(
        @Header("Authorization") accessToken: String
    ): Call<MutableList<DonationRecord>>
}