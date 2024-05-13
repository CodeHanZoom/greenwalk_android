package com.codehanzoom.greenwalk

import android.os.Build
import com.codehanzoom.greenwalk.api.SharedPreferencesManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codehanzoom.greenwalk.nav.NavigationGraph
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.utils.CameraPermissionHandler
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

class MainActivity : ComponentActivity() {

    private lateinit var cameraPermissionHandler: CameraPermissionHandler

    companion object {
        lateinit var prefs: SharedPreferencesManager
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // cameraPermission request
        cameraPermissionHandler = CameraPermissionHandler(this)
        cameraPermissionHandler.checkCameraPermission()
        // 전역변수로 생성해서 onCreate보다 먼저 prefs를 초기화하는게 좋다
        // 다른곳에 데이터를 먼저 넘겨줄 수 있다
        prefs = SharedPreferencesManager(applicationContext)

        // splash screen 적용
        // setContent 전(에 실행
        installSplashScreen()

        setContent {
            GreenWalkTheme {
                NavigationGraph()
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraPermissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
