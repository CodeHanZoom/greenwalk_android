package com.codehanzoom.greenwalk

import android.util.DisplayMetrics
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

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
            ploggingButton()
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Market,
        BottomNavItem.Newsfeed,
        BottomNavItem.Mypage
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        areaMyInfo(name = "나희수", ploggingCount = 328, grade = "GOLD")

        areaAttendance()

        areaCheer(name = "나희수")

        areaListOfDonations()
    }

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

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color("#8CB369".toColorInt()),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10)
                ) {
                    Text("기부하기")
                }
            }
        }
    }
}

@Composable
fun ploggingButton() {
    val displayMetrics = DisplayMetrics()
//    val windowManager = getSystemServices(WINDOW_SERVICE) as WindowManager
//    windowManager.defaultDisplay.getMetrics(displayMetrics)
    FloatingActionButton(
        modifier = Modifier
            .width(400.dp)
            .height(40.dp)
            .offset(10.dp, 660.dp),
        onClick = {

        }
    ) {
        Text(text = "플로깅 시작하기")
    }
}