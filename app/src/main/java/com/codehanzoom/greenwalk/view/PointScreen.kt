package com.codehanzoom.greenwalk.view

import PartnersViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.compose.LargeButton
import com.codehanzoom.greenwalk.compose.SmallButton
import com.codehanzoom.greenwalk.compose.TopBar
import com.codehanzoom.greenwalk.ui.theme.GW_Black100
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.GW_Green200
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.ui.theme.inter_bold
import com.codehanzoom.greenwalk.ui.theme.inter_regular
import com.codehanzoom.greenwalk.viewModel.PloggingInfoViewModel
import com.codehanzoom.greenwalk.viewModel.UserInfoViewModel

@Composable
fun PointScreen(navController: NavHostController) {
    val viewModel = UserInfoViewModel()

    val TAG = "PointScreen"

    val pointState = remember { mutableStateOf(0) }
    val trashCountState = remember { mutableStateOf(0) }
    val ploggingViewModel = PloggingInfoViewModel()

    // UI
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(title = "포인트 적립", navController = navController)
        Divider()
        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            Text(
                text = "${viewModel.getName()} 님,",
                fontFamily = inter_bold,
                color = GW_Black100,
                fontSize = 20.sp
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "오늘 ",
                    fontFamily = inter_regular,
                    color = GW_Black100,
                    fontSize = 20.sp
                )
                Text(
                    text = "GreenWalk",
                    fontFamily = inter_bold,
                    color = GW_Green200,
                    fontSize = 24.sp
                )
                Text(
                    text = "를 하면서",
                    fontFamily = inter_regular,
                    color = GW_Black100,
                    fontSize = 20.sp
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${ploggingViewModel.getTrashCount()}" + "개",
                    fontFamily = inter_bold,
                    color = GW_Green100,
                    fontSize = 24.sp
                )
                Text(
                    text = "의 쓰레기를 주웠어요.",
                    fontFamily = inter_regular,
                    color = GW_Black100,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${ploggingViewModel.getPoint()}" + "포인트",
                    fontFamily = inter_bold,
                    color = GW_Green100,
                    fontSize = 24.sp
                )
                Text(
                    text = "를 적립했어요.",
                    fontFamily = inter_regular,
                    color = GW_Black100,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            PartnerScreen(PartnersViewModel())
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 700.dp)
    ) {
        LargeButton(title = "메인으로") {
            navController.navigate("HomeScreen")
        }
    }
}

@Composable
fun PartnerScreen(viewModel: PartnersViewModel) {

    val partnersResponse by viewModel.partnersResponse.observeAsState()

    Column {
        if (partnersResponse != null) {
            LazyColumn {
                items(partnersResponse!!) { partner ->
                    Row(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.ic_profile_fill),
                                contentDescription = "기부처 아이콘",
                                modifier = Modifier.padding(end = 10.dp)
                            )
                            Column {
                                Text(
                                    text = partner.name,
                                    fontFamily = inter_bold,
                                    color = GW_Black100,
                                    fontSize = 20.sp
                                )
                                Text(
                                    text = "${partner.totalDonationAmount}P",
                                    fontFamily = inter_bold,
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        SmallButton(title = "기부하기")
                    }
                }
            }
        } else {
            Text(text = "로딩 중...")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PointScreenPreview() {
    GreenWalkTheme {
        val navController = rememberNavController()
        PointScreen(navController = navController)
    }
}