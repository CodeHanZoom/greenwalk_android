package com.codehanzoom.greenwalk.nav

import StartScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.CameraScreen
import com.codehanzoom.greenwalk.ui.theme.GW_Green200
import com.codehanzoom.greenwalk.view.HomeScreen
import com.codehanzoom.greenwalk.view.LoginScreen
import com.codehanzoom.greenwalk.view.MarketScreen
import com.codehanzoom.greenwalk.view.MypageScreen
import com.codehanzoom.greenwalk.view.NewsfeedScreen
import com.codehanzoom.greenwalk.view.PloggingScreen
import com.codehanzoom.greenwalk.view.PointScreen
import com.codehanzoom.greenwalk.view.RecordScreen
import com.codehanzoom.greenwalk.view.SignUpScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LoginScreen") {

        composable("StartScreen") {
            StartScreen(navController = navController)
        }
        composable("HomeScreen") {
            HomeScreen(navController = navController)
        }
        composable("MarketScreen") {
            MarketScreen(navController = navController)
        }
        composable("NewsfeedScreen") {
            NewsfeedScreen(navController = navController)
        }
        composable("MypageScreen") {
            MypageScreen(navController = navController)
        }
        composable("LoginScreen") {
            LoginScreen(navController = navController)
        }
        composable("SignUpScreen") {
            SignUpScreen(navController = navController)
        }
        composable("RecordScreen") {
            RecordScreen(navController = navController)
        }
        composable("PloggingScreen") {
            PloggingScreen(navController = navController)
        }
        composable("CameraScreen") {
            CameraScreen(navController = navController)
        }
        composable("PointScreen") {
            PointScreen(navController = navController)
        }
    }
}

@Composable
fun BottomNavigation(navController:NavHostController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Market,
        BottomNavItem.Newsfeed,
        BottomNavItem.Mypage
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                selectedContentColor = GW_Green200,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}