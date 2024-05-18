package com.codehanzoom.greenwalk

import CameraPreviewScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme



@Composable
fun CameraScreen(navController: NavHostController) {
    GreenWalkTheme {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ){
            // A surface container using the 'background' color from the theme
            CameraPreviewScreen(navController = navController)
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CameraScreenPreview() {
    val navController = rememberNavController()
    CameraScreen(navController)
}