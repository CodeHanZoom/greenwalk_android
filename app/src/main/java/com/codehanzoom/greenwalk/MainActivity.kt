package com.codehanzoom.greenwalk

import com.codehanzoom.greenwalk.api.SharedPreferencesManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.nav.NavigationGraph
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.view.HomeScreen

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var prefs: SharedPreferencesManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 전역변수로 생성해서 onCreate보다 먼저 prefs를 초기화하는게 좋다
        // 다른곳에 데이터를 먼저 넘겨줄 수 있다
        prefs = SharedPreferencesManager(applicationContext)
        setContent {
            GreenWalkTheme {
                NavigationGraph()            }
        }
    }
}