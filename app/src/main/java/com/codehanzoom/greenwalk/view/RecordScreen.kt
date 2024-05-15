package com.codehanzoom.greenwalk.view

import com.codehanzoom.greenwalk.viewModel.PloggingViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.compose.LargeButton
import com.codehanzoom.greenwalk.compose.TopBar
import com.codehanzoom.greenwalk.ui.theme.GW_Black100
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.inter_bold
import com.codehanzoom.greenwalk.ui.theme.zenDots


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RecordScreen(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(title = "기록", navController = navController)
        Spacer(modifier = Modifier.height(30.dp))
        CompleteSentence()
        Spacer(modifier = Modifier.height(50.dp))
        PloggingInfo(PloggingViewModel())
        Spacer(modifier = Modifier.height(30.dp))
        LargeButton(title = "사진촬영하기") {
            navController.navigate("CameraScreen")
        }
    }

}

@Composable
fun CompleteSentence() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(220.dp)
    ) {
        Image(
            painterResource(id = R.drawable.ic_plogging_clear),
            contentDescription = "plogging_complete",
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
        )
        Spacer(modifier = Modifier
            .height(20.dp))
        Text(text = "플로깅 완료",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier
            .height(20.dp))
        val text = "오늘도 GreenWalk와 함께\n거리를 깨끗하게 만들었어요!"
        val styledText = "GreenWalk"
        val annotatedString = buildAnnotatedString {
            append(text)
            val startIdx = text.indexOf(styledText)
            if (startIdx >= 0) {
                addStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold, color = GW_Green100),
                    start = startIdx,
                    end = startIdx + styledText.length
                )
            }
        }
        Text(text = annotatedString)
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun PloggingInfo(viewModel: PloggingViewModel) {

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(250.dp)
            .background(GW_Green100, shape = RoundedCornerShape(10.dp))
            .width(320.dp)
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
            fontSize = 12.sp)
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
            fontSize = 12.sp)
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
            fontSize = 12.sp)
    }
}