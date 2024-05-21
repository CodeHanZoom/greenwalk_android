package com.codehanzoom.greenwalk.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.compose.LargeButton
import com.codehanzoom.greenwalk.compose.TopBar
import com.codehanzoom.greenwalk.ui.theme.GW_Black100
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.GW_Green200
import com.codehanzoom.greenwalk.ui.theme.inter_bold
import com.codehanzoom.greenwalk.ui.theme.inter_regular
import com.codehanzoom.greenwalk.ui.theme.zenDots
import com.codehanzoom.greenwalk.viewModel.PloggingViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RecordScreen(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(title = "기록", navController = navController)
        Divider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(30.dp))
        CompleteSentence()
        Spacer(modifier = Modifier.height(50.dp))
        PloggingInfo(PloggingViewModel())
        Spacer(modifier = Modifier.height(30.dp))
    }
    TakePhotoButton(navController)
}

@Composable
fun CompleteSentence() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.height(220.dp)
    ) {
        Image(
            painterResource(id = R.drawable.ic_plogging_clear),
            contentDescription = "plogging_complete",
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier
            .height(20.dp))
        Text(
            text = "플로깅 완료",
            fontFamily = inter_bold,
            fontSize = 32.sp,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "오늘도 ",
                fontFamily = inter_regular,
                fontSize = 18.sp
            )
            Text(
                text = "GreenWalk",
                fontFamily = inter_bold,
                fontSize = 18.sp,
                color = GW_Green200
            )
            Text(
                text = "와 함꼐 ",
                fontFamily = inter_regular,
                fontSize = 18.sp
            )
        }
        Text(
            text = "거리를 깨끗하게 만들었어요!",
            fontFamily = inter_regular,
            fontSize = 18.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun PloggingInfo(viewModel: PloggingViewModel) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(GW_Green100, shape = RoundedCornerShape(10.dp))
            .size(320.dp,250.dp)
            .padding(20.dp)
    ) {
        Text(
            text = viewModel.getTime(),
            fontFamily = zenDots,
            color = Color.White,
            fontSize = 34.sp,
        )
        Text(
            text = "시간",
            fontFamily = inter_bold,
            color = GW_Black100,
            fontSize = 12.sp
        )
        Text(
            text = String.format("%.1f"+" m", viewModel.getTotalDistance()),
            fontFamily = zenDots,
            color = Color.White,
            fontSize = 34.sp,
        )
        Text(
            text = "거리",
            fontFamily = inter_bold,
            color = GW_Black100,
            fontSize = 12.sp
        )
        Text(
            text = "${viewModel.getTotalStep()} stpes",
            fontFamily = zenDots,
            color = Color.White,
            fontSize = 34.sp,
        )
        Text(
            text = "걸음수",
            fontFamily = inter_bold,
            color = GW_Black100,
            fontSize = 12.sp
        )
    }
}
@Composable
fun TakePhotoButton(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.BottomCenter ,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        LargeButton(title = "사진촬영하기") {
            navController.navigate("CameraScreen")
        }
    }
}