package com.codehanzoom.greenwalk.viewModel

import androidx.lifecycle.ViewModel

class PloggingViewModel: ViewModel() {
    // 걸음수를 저장할 변수
    companion object{
        private var totalSteps: Int = 0
        private var totalDistance: Double = 0.0
        private var runningTime: String = ""
    }
    // 걸음수를 설정하는 메서드
    fun setTotalSteps(steps: Int) {
        totalSteps = steps
//        Log.d("com.codehanzoom.greenwalk.viewModel.PloggingViewModel", "set_steps: $totalSteps")
    }
    fun getTotalStep(): Int {
//        Log.d("com.codehanzoom.greenwalk.viewModel.PloggingViewModel", "get_steps: $totalSteps")
        return totalSteps
    }


    fun setTotalDistance(distance: Double) {
        totalDistance = distance
//        Log.d("com.codehanzoom.greenwalk.viewModel.PloggingViewModel", "set_distance: $totalDistance")

    }
    fun getTotalDistance(): Double {
//        Log.d("com.codehanzoom.greenwalk.viewModel.PloggingViewModel", "get_distance: $totalDistance")
        return totalDistance

    }

    fun setTime(time: String) {
        runningTime = time
    }
    fun getTime(): String {
        return runningTime
    }
}