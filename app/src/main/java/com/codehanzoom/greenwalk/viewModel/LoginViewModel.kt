package com.codehanzoom.greenwalk.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.codehanzoom.greenwalk.MainActivity
import com.codehanzoom.greenwalk.api.ApiClient
import com.codehanzoom.greenwalk.model.LoginRequestBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel(private val userInfo: LoginRequestBody) {

    private var errorMessage by mutableStateOf("")

    fun retrofitWork(
        navController: NavHostController,
        isLoginError: (Boolean) -> Unit
    ) {

        val api = ApiClient.retrofit()
        CoroutineScope(Dispatchers.IO).launch {

            val response = api.loginUser(userInfo)
            val result = response.body()
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    // 서버에서 받은 응답 헤더에서 Authorization 값을 추출
                    val accessToken = response.headers()["Authorization"]

                    if (accessToken != null) {
                        // shared preference에 토큰 저장
                        MainActivity.prefs.setString("accessToken", accessToken)

                        if (result != null) {

                            when (result.status) {
                                200 -> {
                                    Log.d(TAG, "로그인 / status : " + "${result.status}")
                                    Log.d(TAG, "로그인 / data : " + "${result.data}")
                                    Log.d(TAG, "로그인 / jwt : $accessToken")
//                                    navController.navigate("HomeScreen")
                                }
                                else -> {
                                    isLoginError(true)
                                }
                            }
                        } else {
                            Log.d("로그인 / 통신오류 : ", response.body().toString())
                        }
                    }
                }
            }
        }
    }
}