package com.codehanzoom.greenwalk.nav

import com.codehanzoom.greenwalk.R

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Home :
        BottomNavItem(R.string.text_home, R.drawable.ic_home, R.string.text_home.toString())

    object Market :
        BottomNavItem(R.string.text_market, R.drawable.ic_market, R.string.text_market.toString())

    object Newsfeed : BottomNavItem(
        R.string.text_newsfeed,
        R.drawable.ic_newsfeed,
        R.string.text_newsfeed.toString()
    )

    object Mypage :
        BottomNavItem(R.string.text_mypage, R.drawable.ic_mypage, R.string.text_mypage.toString())
}