package com.codehanzoom.greenwalk.compose

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehanzoom.greenwalk.ui.theme.zenDots
import com.codehanzoom.greenwalk.viewModel.PloggingViewModel
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationCallback

@Composable
fun GetDistance() {
    var totalDistance by remember { mutableStateOf(0.0) }
    val context = LocalContext.current as Activity
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val LOCATION_PERMISSION_REQUEST_CODE = 100
    val viewModel: PloggingViewModel = viewModel()

    var previousLocation: android.location.Location? = null

    fun updateDistance(location: android.location.Location) {
        previousLocation?.let { previous ->
            val distanceInMeters = previous.distanceTo(location)
            totalDistance += distanceInMeters
        }
        previousLocation = location
    }

    DisposableEffect(key1 = fusedLocationClient) {
        val locationRequest = LocationRequest.create().apply {
            interval = 1000 // 1초마다 위치 업데이트
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    // 새로운 위치가 감지될 때마다 거리를 업데이트
                    updateDistance(location)
                    viewModel.setTotalDistance(totalDistance)
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)

        onDispose {
            // 이펙트가 종료될 때 위치 업데이트 중지
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }

    Text(
        text = String.format("%.1f"+" m", totalDistance), // 소수점 한 자리까지 포맷 지정
        fontFamily = zenDots,
        color = Color.White,
        fontSize = 34.sp,
    )
}
