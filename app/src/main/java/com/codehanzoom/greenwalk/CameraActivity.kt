package com.codehanzoom.greenwalk


import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.codehanzoom.greenwalk.publicCompose.SmallButton
import com.codehanzoom.greenwalk.publicCompose.TopBar
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun CameraActivity() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        TopBar(title = "사진촬영")
        Spacer(modifier = Modifier.height(40.dp))
        cameraScreen()
        SmallButton(title = "촬영하기")
    }
}


@Composable
fun cameraScreen() {
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val cameraSelector = remember { CameraSelector.DEFAULT_BACK_CAMERA }
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val previewView = androidx.camera.view.PreviewView(context)
            val executor = ContextCompat.getMainExecutor(context)
            cameraProviderFuture.addListener(
                {
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = androidx.camera.core.Preview.Builder().build()
                    val camera = cameraProvider.bindToLifecycle(
                        context as androidx.lifecycle.LifecycleOwner,
                        cameraSelector,
                        preview
                    )
//                    preview.setSurfaceProvider(previewView.createSurfaceProvider(camera.cameraInfo))
                },
                executor
            )
            previewView
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CameraActivityPreview() {
    GreenWalkTheme {
        CameraActivity()
    }
}