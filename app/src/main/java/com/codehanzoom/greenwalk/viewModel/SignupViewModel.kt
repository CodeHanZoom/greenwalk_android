package com.codehanzoom.greenwalk.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.navigation.NavHostController
import com.codehanzoom.greenwalk.api.ApiClient
import com.codehanzoom.greenwalk.model.SignUpRequestBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(private val userInfo: SignUpRequestBody) {
    fun retrofitWork(navController: NavHostController) {

        val api = ApiClient.retrofit()
        CoroutineScope(Dispatchers.IO).launch {

            val response = api.addUser(userInfo)
            val result = response.body()
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {

                    if (result != null) {

                        when (result.status) {
                            200 -> {
                                Log.d(TAG, "회원가입 / status : " + "${result.status}")
                                Log.d(TAG, "회원가입 / data : " + "${result.data}")
                                navController.navigate("LoginScreen")
                            }

                            400 -> {
                                Log.d(TAG, "회원가입 / status : " + "${result.status}")
                                Log.d(TAG, "회원가입 / data : " + "${result.data}")

                            }
                        }
                    } else {
                        Log.d("회원가입 / API 통신실패 : ", response.body().toString())
                    }
                }
            }
        }
    }
}