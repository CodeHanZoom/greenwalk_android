package com.codehanzoom.greenwalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme



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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenWalkTheme {
                Main()
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewBody() {
    GreenWalkTheme {
        Main()
    }

}