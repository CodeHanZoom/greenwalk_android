package com.codehanzoom.greenwalk.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehanzoom.greenwalk.ui.theme.zenDots
import com.codehanzoom.greenwalk.api.SensorClient
import com.codehanzoom.greenwalk.viewModel.PloggingViewModel

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun GetStep() {
    val context = LocalContext.current
    val viewModel: PloggingViewModel = viewModel()
    var steps by remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        val sensorClient = SensorClient(context, object : SensorClient.StepCountListener {
            override fun onStepCountChanged(stepCount: Int) {
                viewModel.setTotalSteps(stepCount)
                steps = viewModel.getTotalStep()
            }
        })
        sensorClient.initialize()
        sensorClient.start()
        onDispose {
            sensorClient.stop()
        }
    }

    Text(
        text = "${steps} stpes",
        fontFamily = zenDots,
        color = Color.White,
        fontSize = 34.sp,
    )
}