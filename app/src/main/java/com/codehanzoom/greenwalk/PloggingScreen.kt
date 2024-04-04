package com.codehanzoom.greenwalk

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehanzoom.greenwalk.publicCompose.TopBar
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.GW_Red100
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun PloggingScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(title = "플로깅")
        Column(
            modifier = Modifier
                .height(300.dp)
                .background(GW_Green100)
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "00 : 00",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "시간")
            Text(
                text = "0.00 km",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "킬로미터")
            Text(
                text = "0000 steps",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "걸음수")
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.pic_test_map),
                contentDescription = "pic_test_map",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
    StopButton()
}

@Composable
fun StopButton() {
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
        onClick = {},
        containerColor = GW_Red100
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_plogging_stop),
            contentDescription = "ic_plogging_stop"
        )
    }
}

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
fun PreviewPloggingScreen() {
    GreenWalkTheme {
        PloggingScreen()
    }
}
