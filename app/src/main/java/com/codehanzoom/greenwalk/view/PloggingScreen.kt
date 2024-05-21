package com.codehanzoom.greenwalk.view

import com.codehanzoom.greenwalk.utils.GetDistance
import com.codehanzoom.greenwalk.utils.GetStep
import com.codehanzoom.greenwalk.utils.GetTime
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.compose.TopBar
import com.codehanzoom.greenwalk.ui.theme.GW_Black100
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.GW_Red100
import com.codehanzoom.greenwalk.ui.theme.inter_bold


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun PloggingScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "플로깅", navController = navController)
        Divider(thickness = 0.5.dp, color = Color.Gray)
        Column(
            modifier = Modifier
                .background(GW_Green100)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp
                    )
            ) {
                // 시간 컴포즈
                GetTime()
                Text(
                    text = "시간",
                    fontFamily = inter_bold,
                    color = GW_Black100,
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp
                    )
            ) {
                // 이동거리 컴포즈
                GetDistance()
                Text(
                    text = "거리",
                    fontFamily = inter_bold,
                    color = GW_Black100,
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                // 걸음수 컴포즈
                GetStep()
                Text(
                    text = "걸음수",
                    fontFamily = inter_bold,
                    color = GW_Black100,
                    fontSize = 12.sp
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Naver Map api
            MapScreen()
        }
    }
    StopButton(navController)
}
@Composable
fun StopButton(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.BottomCenter ,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_plogging_stop),
            contentDescription = "Plogging Stop",
            modifier = Modifier.clickable {
                navController.navigate("RecordScreen")
            }
        )
    }
}


