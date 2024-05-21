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
        var totalWalkingDistance: Double = 0.0
        var accumulatedPoint: Int = 0
    }

    // id getter / setter
    fun getId(): Int {
        return id
    }

    fun setId(_id: Int) {
        id = _id
    }

    // name getter / setter
    fun getName(): String {
        return name
    }

    fun setName(_name: String) {
        name = _name
    }

    // email getter / setter
    fun getEmail(): String {
        return email
    }

    fun setEmail(_email: String) {
        email = _email
    }

    // totalPoint getter / setter
    fun getTotalPoint(): Int {
        return totalPoint
    }

    fun setTotalPoint(_totalPoint: Int) {
        totalPoint = _totalPoint
    }

    // totalDonation getter / setter
    fun getTotalDonation(): Int {
        return totalDonation
    }

    fun setTotalDonation(_totalDonation: Int) {
        totalDonation = _totalDonation
    }

    // totalStep getter / setter
    fun getTotalStep(): Int {
        return totalStep
    }

    fun setTotalStep(_totalStep: Int) {
        totalStep = _totalStep
    }

    // totalTrashCount getter / setter
    fun getTotalTrashCount(): Int {
        return totalTrashCount
    }

    fun setTotalTrashCount(_totalTrashCount: Int) {
        totalTrashCount = _totalTrashCount
    }

    // totalWalkingDistance getter / setter
    fun getTotalWalkingDistance(): Double {
        return totalWalkingDistance
    }

    fun setTotalWalkingDistance(_totalWalkingDistance: Double) {
        totalWalkingDistance = _totalWalkingDistance
    }


    // accumulatedPoint getter / setter
    fun getAccumulatedPoint(): Int {
        return accumulatedPoint
    }

    fun setAccumulatedPoint(_accumulatedPoint: Int) {
        accumulatedPoint = _accumulatedPoint
    }
}
