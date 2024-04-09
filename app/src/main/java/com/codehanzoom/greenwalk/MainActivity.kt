package com.codehanzoom.greenwalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.utils.CameraPermissionHandler

class MainActivity : ComponentActivity() {
    private lateinit var cameraPermissionHandler: CameraPermissionHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        checkCameraPermission()
        cameraPermissionHandler = CameraPermissionHandler(this)
        cameraPermissionHandler.checkCameraPermission()
        setContent {
            GreenWalkTheme {
                Main()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraPermissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBody() {
    GreenWalkTheme {
        Main()
    }

}