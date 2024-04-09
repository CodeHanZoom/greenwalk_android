//package com.codehanzoom.greenwalk
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.rememberNavController
//import com.codehanzoom.greenwalk.nav.BottomNavigation
//import com.codehanzoom.greenwalk.nav.NavigationGraph
//import com.codehanzoom.greenwalk.view.areaHeader
//
//@Composable
//fun Main() {
//    val navController = rememberNavController()
//    Scaffold(
//        topBar = {
//            areaHeader()
//        },
//        bottomBar = {
//            BottomNavigation()
//        }
//    ) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxWidth()
//                .fillMaxHeight()
//        ) {
////            NavigationGraph()
//        }
//    }
//}