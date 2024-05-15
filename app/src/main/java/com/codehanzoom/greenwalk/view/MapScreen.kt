package com.codehanzoom.greenwalk.view

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.util.FusedLocationSource

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        var mapProperties by remember {
            mutableStateOf(
                MapProperties(
//                    maxZoom = 18.0,
//                    minZoom = 18.0,
                    locationTrackingMode = LocationTrackingMode.Follow,
                    )
                )
        }

        var mapUiSettings by remember {
            mutableStateOf(
                MapUiSettings(
                    isLocationButtonEnabled = false,
                    )
            )
        }

        val seoul = LatLng(37.532600, 127.024612)
        val cameraPositionState: CameraPositionState = rememberCameraPositionState {
            // 카메라 초기 위치를 설정합니다.
            position = CameraPosition(seoul, 18.0)
        }

        Box(Modifier.fillMaxSize()) {

            NaverMap(
                locationSource = rememberFusedLocationSource(),
                properties = mapProperties,
                uiSettings = mapUiSettings,
                cameraPositionState = cameraPositionState,
            )

//            Column {
//                Button(onClick = {
//                    mapProperties = mapProperties.copy(
//                        isBuildingLayerGroupEnabled = !mapProperties.isBuildingLayerGroupEnabled
//                    )
//                }) {
//                    Text(text = "Toggle isBuildingLayerGroupEnabled")
//                }
//                Button(onClick = {
//                    mapUiSettings = mapUiSettings.copy(
//                        isLocationButtonEnabled = !mapUiSettings.isLocationButtonEnabled
//                    )
//                }) {
//                    Text(text = "Toggle isLocationButtonEnabled")
//                }
//            }
        }
    }
}


