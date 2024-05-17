package com.codehanzoom.greenwalk.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.nav.BottomNavigation
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.utils.getGrade
import com.codehanzoom.greenwalk.viewModel.UserInfoViewModel

@Composable
fun MypageScreen(navController: NavHostController) {
    val viewModel = UserInfoViewModel()
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
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Spacer(Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image (
                                painter = painterResource(id = R.drawable.ic_account_circle),
                                contentDescription = "image for profile",
                                modifier = Modifier.size(60.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "${viewModel.getName()}님",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }

                        Spacer(Modifier.height(15.dp))
                        Text(text = "${getGrade(viewModel.getTotalPoint())} 등급",
                            fontSize = 16.sp)
                    }
                    Spacer(Modifier.height(20.dp))
                }

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
                            text = "활동기록",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(Modifier.height(15.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "플로깅 거리",
                                    fontSize = 16.sp)
                                Text(text = "${viewModel.getTotalWalkingDistance()} km",
                                    color = Color("#8CB369".toColorInt()),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp)
                            }
                            Spacer(Modifier.width(20.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "보유 포인트",
                                    fontSize = 16.sp)
                                Text(text = "${viewModel.getTotalPoint()} P",
                                    color = Color("#8CB369".toColorInt()),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp)
                            }
                            Spacer(Modifier.width(20.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "주운 쓰레기",
                                    fontSize = 16.sp)
                                Text(text = "${viewModel.getTotalTrashCount()} 개",
                                    color = Color("#8CB369".toColorInt()),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp)
                            }
                        }
                    }
                }

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
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "기부내역",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(Modifier.height(15.dp))

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyPageScreen() {
    GreenWalkTheme {
        val navController = rememberNavController()
        MypageScreen(navController)
    }
}