package com.codehanzoom.greenwalk.nav

import com.codehanzoom.greenwalk.R

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Home : BottomNavItem
        (R.string.text_home, R.drawable.ic_home, "HomeScreen")

    object Market : BottomNavItem
        (R.string.text_market, R.drawable.ic_market,"MarketScreen")

    object Newsfeed : BottomNavItem(
        R.string.text_newsfeed, R.drawable.ic_newsfeed,"NewsfeedScreen"
    )

    object Mypage : BottomNavItem
        (R.string.text_mypage, R.drawable.ic_mypage, "MypageScreen")
}