package com.codehanzoom.greenwalk.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codehanzoom.greenwalk.HomeScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.screenRoute) {
        composable(BottomNavItem.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNavItem.Market.screenRoute) {
            //MarketScreen()
        }
        composable(BottomNavItem.Newsfeed.screenRoute) {
            //NewsfeedScreen()
        }
        composable(BottomNavItem.Mypage.screenRoute) {
            //MypageScreen()
        }
    }
}