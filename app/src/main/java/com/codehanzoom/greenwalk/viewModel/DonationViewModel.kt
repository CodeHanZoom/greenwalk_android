package com.codehanzoom.greenwalk.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codehanzoom.greenwalk.MainActivity
import com.codehanzoom.greenwalk.api.DonationApiClient
import com.codehanzoom.greenwalk.api.DonationService
import com.codehanzoom.greenwalk.model.DonationsRequestBody
import kotlinx.coroutines.launch

class DonationViewModel : ViewModel() {
    val TAG = "DonationViewModel"

    fun fetchDonate(partnerId: Int, donationMoney: Int) {
        val accessToken = MainActivity.prefs.getString("accessToken", "")

        viewModelScope.launch {
            val donationBody = DonationsRequestBody(partnerId, donationMoney)
            try {
                val response = DonationApiClient.donationService.sendDonation("Bearer $accessToken", donationBody)
                if (response.isSuccessful) {
                    when(response.code()) {
                        200 -> {
                            Log.d(TAG, "donate: 기부성공")
                        }
                    }
                }else {
                    Log.d(TAG, "onResponse Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d(TAG, "fetchDonate: ${e.message}")
            }

        }
    }
}