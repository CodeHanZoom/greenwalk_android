package com.codehanzoom.greenwalk.viewModel

import androidx.lifecycle.ViewModel

class UserInfoViewModel : ViewModel() {
    companion object {
        var id = 0
        var name = ""
        var email = ""
        var totalPoint = 0
        var totalDonation = 0
        var totalStep = 0
        var totalTrashCount = 0
        var totalWalkingDistance = 0
    }

    fun getName(): String {
        return name
    }

    fun setName(_name: String) {
        name = _name
    }
}
