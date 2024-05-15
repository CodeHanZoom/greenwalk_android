package com.codehanzoom.greenwalk.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.codehanzoom.greenwalk.model.UserInfoResponseBody
import com.codehanzoom.greenwalk.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var userInfo: UserInfoResponseBody? = null
    private var accessToken = ""

    fun loadUserInfo(accessToken: String) {
        this.accessToken = accessToken
        // Retrofit를 사용하여 서버로부터 사용자 정보를 받아옴
        RetrofitClient.instance.getUserInfo("Bearer $accessToken").enqueue(object : retrofit2.Callback<UserInfoResponseBody> {
            override fun onResponse(call: Call<UserInfoResponseBody>, response: Response<UserInfoResponseBody>) {
                if (response.isSuccessful) {
                    userInfo = response.body()
                } else {
                    Log.e("HomeViewModel", "Response Error : ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<UserInfoResponseBody>, t: Throwable) {
                Log.e("HomeViewModel", "Network Error : ${t.message}")
            }
        })
    }
}
