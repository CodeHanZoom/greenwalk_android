package com.codehanzoom.greenwalk.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codehanzoom.greenwalk.MainActivity
import com.codehanzoom.greenwalk.api.PartnersClient
import com.codehanzoom.greenwalk.model.PartnersResponseBody
import kotlinx.coroutines.launch

class PartnersViewModel : ViewModel() {

    val TAG = "PartnersViewModel"

    private val _partners = MutableLiveData<List<PartnersResponseBody>>()
    val partners: LiveData<List<PartnersResponseBody>> get() = _partners

    init {
        fetchPartners()
    }

    private fun fetchPartners() {
        val accessToken = MainActivity.prefs.getString("accessToken", "")

        viewModelScope.launch {
            try {
                val response = PartnersClient.partnerService.getPartners("Bearer $accessToken")
                _partners.value = response
            } catch (e: Exception) {
                // Handle exception
                Log.d(TAG, "fetchPartners: ${e.message}")
            }
        }
    }

}
