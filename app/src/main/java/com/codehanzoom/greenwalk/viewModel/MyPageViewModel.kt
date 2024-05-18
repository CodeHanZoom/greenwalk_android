package com.codehanzoom.greenwalk.viewModel

import androidx.lifecycle.ViewModel
import com.codehanzoom.greenwalk.model.DonationRecord

class MyPageViewModel: ViewModel() {
    companion object {
        private var donationList: MutableList<DonationRecord> = mutableListOf()
    }

    // donationList getter / setter
    fun getDonationList(): MutableList<DonationRecord> {
        return donationList
    }

    fun setDonationList(_donationList: MutableList<DonationRecord>) {
        donationList = _donationList
    }
}