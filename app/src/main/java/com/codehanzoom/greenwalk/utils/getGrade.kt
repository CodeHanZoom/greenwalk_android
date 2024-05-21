package com.codehanzoom.greenwalk.utils

// getGrade
// totalPoint에 따라 등급을 반환하는 함수
// 0~299 300~499 500이상 세 구간으로 구성
fun getGrade(accumulatedPoint: Int): String {
    if (accumulatedPoint < 500)
        return "BRONZE"
    else if (accumulatedPoint < 1000)
        return "SILVER"
    else
        return "GOLD"
}