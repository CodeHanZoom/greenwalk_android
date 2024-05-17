package com.codehanzoom.greenwalk.api

import com.codehanzoom.greenwalk.model.PartnersResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface PartnerService {
    @GET("partners")
    suspend fun getPartners(
        @Header("Authorization") accessToken: String
    ): List<PartnersResponseBody>

}

object PartnersClient {
    private const val BASE_URL = "http://aws-v5-beanstalk-env.eba-znduyhtv.ap-northeast-2.elasticbeanstalk.com/"

    val partnerService: PartnerService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PartnerService::class.java)
    }
}