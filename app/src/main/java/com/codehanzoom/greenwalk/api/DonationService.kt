package com.codehanzoom.greenwalk.api

import com.codehanzoom.greenwalk.model.DonationsRequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface DonationService {
    @Headers("Content-Type: application/json")
    @POST("donations")
    suspend fun sendDonation(
        @Header("Authorization") accessToken: String,
        @Body donationsInfo: DonationsRequestBody
    ): Response<Void>
}

object DonationApiClient {
    private const val BASE_URL = "http://aws-v5-beanstalk-env.eba-znduyhtv.ap-northeast-2.elasticbeanstalk.com/"

     val donationService: DonationService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DonationService::class.java)
    }
}