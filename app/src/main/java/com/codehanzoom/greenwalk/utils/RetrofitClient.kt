package com.codehanzoom.greenwalk.utils

import com.codehanzoom.greenwalk.api.ApiService
import com.codehanzoom.greenwalk.api.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://aws-v5-beanstalk-env.eba-znduyhtv.ap-northeast-2.elasticbeanstalk.com/"

    val instance: UserService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(UserService::class.java)
    }
}
