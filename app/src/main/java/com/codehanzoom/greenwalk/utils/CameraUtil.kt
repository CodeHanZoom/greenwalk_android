package com.codehanzoom.greenwalk.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CameraPermissionHandler(private val activity: Activity) {
    private val cameraPermissionCode = 123 // Unique request code for camera permission

    fun checkCameraPermission() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.CAMERA
            ) -> {
                // Camera permission already granted
                // Implement camera related code
            }
            else -> {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(android.Manifest.permission.CAMERA),
                    cameraPermissionCode
                )
            }
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == cameraPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission granted
                // Implement camera related code
            } else {
                // Camera permission denied
            }
        }
    }
}