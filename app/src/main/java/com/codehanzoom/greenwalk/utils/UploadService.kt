package com.codehanzoom.greenwalk.utils

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadService {
    @Multipart
    @POST("photos") // 서버 엔드포인트
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("step") step: RequestBody,
        @Part("walkingDistance") walking: RequestBody,
        @Header("Authorization") accessToken: String
    ): Call<ResponseBody>
}
//interface UploadService {
//    @POST("photos")
//    fun uploadImage(
//        @Body image: RequestBody, // 이미지 데이터는 Base64 인코딩된 문자열로 보낸다
//        @Query("step") step: Int,
//        @Query("walkingDistance") walking: Float,
//        @Header("accessToken") accessToken: String
//    ): Call<ResponseBody>
//}
