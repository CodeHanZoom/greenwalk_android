package com.codehanzoom.greenwalk

import com.codehanzoom.greenwalk.nav.BottomNavigation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.nav.NavigationGraph
import com.codehanzoom.greenwalk.publicCompose.LargeButton
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun Main() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            areaHeader()
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        areaMyInfo(name = "나희수", ploggingCount = 328, grade = "GOLD")

        areaAttendance()

        areaCheer(name = "나희수")

        areaListOfDonations()
    }

    ploggingButton()
}

@Composable
fun areaHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "GreenWalk",
            color = Color("#8CB369".toColorInt()),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(R.drawable.notification_picture),
            contentDescription = "image for notification",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun areaMyInfo(name: String, ploggingCount: Int, grade: String) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(name + "님")
            Text(ploggingCount.toString() + "개의 쓰레기를 플로깅 하셨어요")
            Spacer(modifier = Modifier.height(10.dp))
            Text("현재 " + name + " 님은 " + grade + "등급 입니다.")
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "P",
                color = Color("#8CB369".toColorInt()),
                fontWeight = FontWeight.Bold
            )
            Text("보유 포인트")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "Km",
                color = Color("#8CB369".toColorInt()),
                fontWeight = FontWeight.Bold
            )
            Text("플로깅 거리")
        }
    }
}

@Composable
fun areaAttendance() {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
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
                for (i: Int in 1..7) {
                    Column {
                        Text("월")
                        Text("1")
                    }
                }
            }
        }
    }
}

@Composable
fun areaCheer(name: String) {
    Text(
        "$name 님,\n플로깅해서 모은 포인트를 기부해보세요!", modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
fun areaListOfDonations() {
    for (i: Int in 1..8) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Picture")
                Column(
                    modifier = Modifier.width(50.dp)
                ) {
                    Text("기부처")
                    Text("500P")
                }

                LargeButton("기부하기")
            }
        }
    }
}

@Composable
fun ploggingButton() {
    FloatingActionButton(
        modifier = Modifier
            .width(400.dp)
            .height(40.dp)
            .offset(10.dp, 660.dp),
        onClick = { /* TODO */ },
        containerColor = Color(0xff8CB369),
        contentColor = Color.White
    ) {
        Text(
            text = "플로깅 시작하기",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
    }
}

@Composable
@Preview
fun PreviewHome() {
    GreenWalkTheme {
        Main()
    }
}