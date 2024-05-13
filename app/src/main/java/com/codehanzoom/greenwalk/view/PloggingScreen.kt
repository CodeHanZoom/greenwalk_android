package com.codehanzoom.greenwalk.view

import com.codehanzoom.greenwalk.compose.GetDistance
import com.codehanzoom.greenwalk.compose.GetStep
import com.codehanzoom.greenwalk.compose.GetTime
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.codehanzoom.greenwalk.ui.theme.inter


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun PloggingScreen(navController: NavHostController) {


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "플로깅", navController = navController)

        Column(
            modifier = Modifier
                .height(240.dp)
                .background(GW_Green100)
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                // 시간 컴포즈
                GetTime()
                Text(
                    text = "시간",
                    fontFamily = inter,
                    color = GW_Black100,
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                // 이동거리 컴포즈
                GetDistance()
                Text(
                    text = "거리",
                    fontFamily = inter,
                    color = GW_Black100,
                    fontSize = 12.sp)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                // 걸음수 컴포즈
                GetStep()
                Text(
                    text = "걸음수",
                    fontFamily = inter,
                    color = GW_Black100,
                    fontSize = 12.sp)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

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
    // 화면정보 불러오기
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    // x, y 좌표 설정
    // x: 전체 width에서 width 70dp를 뺀 후 1/2 gap
    // y: height 90% 기준으로 설정
    val newX = (screenWidth.value - 70) / 2
    val newY = screenHeight.value * 0.9

    FloatingActionButton(
        modifier = Modifier
            .width(70.dp)
            .height(70.dp)
            .offset(newX.toInt().dp, newY.toInt().dp),
        onClick = {
            navController.navigate("RecordScreen") {
            }
        },
        containerColor = GW_Red100
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_plogging_stop),
            contentDescription = "ic_plogging_stop"
        )
    }
}


