package com.codehanzoom.greenwalk.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.codehanzoom.greenwalk.nav.BottomNavigation

@Composable
fun  MarketScreen(navController: NavHostController) {
        Scaffold(
                topBar = {
                        AreaHeader()
                },
                bottomBar = {
                        BottomNavigation(navController = navController)
                }
        ) { innerPadding ->
                Box(
                        modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxWidth()
                                .fillMaxHeight()
                ) {
                        {/* 퍼블리싱할 페이지 */}
                }
        }
}