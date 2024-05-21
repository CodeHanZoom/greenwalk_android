package com.codehanzoom.greenwalk.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codehanzoom.greenwalk.ui.theme.zenDots
import com.codehanzoom.greenwalk.viewModel.PloggingViewModel
import java.util.Timer
import java.util.TimerTask

@Composable
fun GetTime() {
    var hours by remember { mutableIntStateOf(0) }
    var minutes by remember { mutableIntStateOf(0) }
    var seconds by remember { mutableIntStateOf(0) }
    val viewModel: PloggingViewModel = viewModel()

    DisposableEffect(Unit) {
        val timerTask = object : TimerTask() {
            override fun run() {
                seconds++
                if (seconds == 60) {
                    seconds = 0
                    minutes++
                    if (minutes == 60) {
                        minutes = 0
                        hours++
                        if (hours == 24) {
                            hours = 0
                        }
                    }
                }
            }
        }
        val timer = Timer()
        timer.scheduleAtFixedRate(timerTask, 0, 1000)
        onDispose {
            hours = 0
            minutes = 0
            seconds = 0
        }
    }
    var timerToString: String = String.format("%02d:%02d:%02d", hours, minutes, seconds)
    viewModel.setTime(timerToString)
    Text(
        text = timerToString,
        fontFamily = zenDots,
        color = Color.White,
        fontSize = 34.sp,
    )
}
