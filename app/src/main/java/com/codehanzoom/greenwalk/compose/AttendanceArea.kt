package com.codehanzoom.greenwalk.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import java.util.Calendar
import java.util.Date

@Composable
fun AttendanceArea() {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = "출석체크",
                color = Color("#8CB369".toColorInt()),
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // 오늘(Today) 받기
                val currentTime: Date = Calendar.getInstance().time
                // 요일(Day) 목록
                val dayList: List<String> = listOf("일", "월", "화", "수", "목", "금", "토")
                val todayDate = currentTime.date
                val todayDay = currentTime.day

                // 앞 3일 표시
                for (i: Int in -3..3) {
                    Column (
                        modifier = Modifier
                            .padding(3.dp)
                    ){
                        if (i == 0) {
                            Text("${dayList[(todayDay+i+7)%7]}",
                                modifier = Modifier
                                    .drawBehind {
                                        drawRect(
                                            color = GW_Green100
                                        )
                                    }
                            )
                        }
                        else {
                            Text("${dayList[(todayDay+i+7)%7]}")
                        }
                        Text("${todayDate+i}")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAttendacneArea() {
    GreenWalkTheme {
        AttendanceArea()
    }
}