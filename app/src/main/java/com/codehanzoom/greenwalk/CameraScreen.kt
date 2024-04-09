package com.codehanzoom.greenwalk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.utils.CameraPreviewScreen


@Composable
fun CameraScreen() {
    GreenWalkTheme {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ){
            // A surface container using the 'background' color from the theme
            CameraPreviewScreen()
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CameraScreenPreview() {
    CameraScreen()
}