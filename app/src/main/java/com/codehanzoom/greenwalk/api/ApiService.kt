package com.codehanzoom.greenwalk.api

import com.codehanzoom.greenwalk.model.LoginRequestBody
import com.codehanzoom.greenwalk.model.LoginResponseBody
import com.codehanzoom.greenwalk.model.SignUpRequestBody
import com.codehanzoom.greenwalk.model.SignUpResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("auth/join") // 엔드포인트
    suspend fun addUser(@Body userInfo: SignUpRequestBody): Response<SignUpResponseBody> // Call은 흐름처리 기능을 제공해줌
    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun loginUser(@Body userInfo: LoginRequestBody): Response<LoginResponseBody>
}