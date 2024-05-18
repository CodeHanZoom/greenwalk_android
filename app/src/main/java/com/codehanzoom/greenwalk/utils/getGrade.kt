package com.codehanzoom.greenwalk.utils

// getGrade
// totalPoint에 따라 등급을 반환하는 함수
// 0~299 300~499 500이상 세 구간으로 구성
fun getGrade(totalPoint: Int): String {
    if (totalPoint < 300)
        return "BRONZE"
    else if (totalPoint < 500)
        return "SILVER"
    else
        return "GOLD"
}