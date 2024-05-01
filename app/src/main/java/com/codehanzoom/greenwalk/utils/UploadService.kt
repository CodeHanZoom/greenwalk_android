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
    @POST("photos") // 서버 엔드포인트에 따라 적절히 변경해주세요
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("step") step: RequestBody,
        @Part("walkingDistance") walking: RequestBody,
        @Header("Authorization") accessToken: String
    ): Call<ResponseBody>
}

//private fun uploadImageWithRetrofit(imageFile: File, step: Int, walking: Float, serverUrl: String) {
//    val retrofit = Retrofit.Builder()
//        .baseUrl(serverUrl)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    val service = retrofit.create(UploadService::class.java)
//
//    // 이미지 파일을 RequestBody로 변환
//    val requestFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
//    val body = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
//
//    // step과 walking을 RequestBody로 변환
//    val stepBody = step.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//    val walkingBody = walking.toString().toRequestBody("text/plain".toMediaTypeOrNull())
//
//    // 업로드 요청 생성
//    val call = service.uploadImage(body, stepBody, walkingBody)
//    call.enqueue(object : Callback<ResponseBody> {
//        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//            if (response.isSuccessful) {
//                println("Image uploaded successfully!")
//            } else {
//                println("Failed to upload image: ${response.code()}")
//            }
//        }
//
//        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//            println("Network error: ${t.message}")
//        }
//    })
//}
