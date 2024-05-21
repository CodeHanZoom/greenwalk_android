package com.codehanzoom.greenwalk.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.MainActivity
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.model.DonationRecord
import com.codehanzoom.greenwalk.nav.BottomNavigation
import com.codehanzoom.greenwalk.ui.theme.GW_Gray100
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.GW_Yellow100
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.ui.theme.inter_bold
import com.codehanzoom.greenwalk.ui.theme.inter_regular
import com.codehanzoom.greenwalk.utils.RetrofitClient
import com.codehanzoom.greenwalk.utils.convertMetersToKilometers
import com.codehanzoom.greenwalk.utils.getGrade
import com.codehanzoom.greenwalk.viewModel.MyPageViewModel
import com.codehanzoom.greenwalk.viewModel.UserInfoViewModel
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.round

@Composable
fun MypageScreen(navController: NavHostController) {
    val viewModel = UserInfoViewModel()
//    var donationList: MutableList<DonationRecord> = mutableListOf()
    val donationViewModel = MyPageViewModel()
    val accessToken = MainActivity.prefs.getString("accessToken", "")

    RetrofitClient.instance.getDonationList("Bearer $accessToken")
        .enqueue(object : retrofit2.Callback<MutableList<DonationRecord>> {
            override fun onResponse(
                call: Call<MutableList<DonationRecord>>,
                response: Response<MutableList<DonationRecord>>
            ) {
                println(response.body())
                if (response.isSuccessful) {
                    donationViewModel.setDonationList(response.body() ?: mutableListOf())
                    Log.d("viewmodel test", viewModel.getName())
                    Log.d("MyPageScreen", accessToken.toString())

                } else {
                    Log.e("HomeScreen", "Response Error : ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MutableList<DonationRecord>>, t: Throwable) {
                Log.e("HomeScreen", "Network Error : ${t.message}")
            }
        })

    Scaffold(
        topBar = {
            AreaHeader()
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        Divider(thickness = 0.5.dp, color = Color.Gray)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
            ) {
                // 유저 정보
                Column(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_account_circle),
                            contentDescription = "image for profile",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = "${viewModel.getName()}님",
                            fontFamily = inter_bold,
                            fontSize = 24.sp
                        )
                    }
                    Spacer(Modifier.height(28.dp))
                    Row {
                        Text(
                            text = getGrade(viewModel.getAccumulatedPoint()),
                            fontFamily = inter_bold,
                            color = GW_Yellow100,
                            fontSize = 16.sp
                        )
                        Text(
                            text = " 등급",
                            fontFamily = inter_bold,
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // 총 항목 수와 완료된 항목 수
                        val target = 500
                        // 프로그레스 바의 진행도 계산 (완료된 항목 수 / 총 항목 수)
                        val progress = viewModel.getAccumulatedPoint() / target.toFloat()
                        LinearProgressIndicator(
                            color = GW_Green100,
                            trackColor = GW_Gray100,
                            progress = progress,
                            modifier = Modifier
                                .size(230.dp, 15.dp)
                                .clip(RoundedCornerShape(16.dp)) // 코너를 둥글게 설정
                        )
                        Row {
                            Text(
                                text = "${viewModel.getAccumulatedPoint()} ",
                                fontFamily = inter_bold,
                                fontSize = 16.sp,
                                color = GW_Green100
                            )
                            Text(text = "/ ${target}",
                                fontFamily = inter_bold,
                                fontSize = 16.sp,
                                color = GW_Gray100
                            )
                        }
                    }
                }
                Divider(thickness = 20.dp, color = Color(0XFFF8F8F8))
                // 플로깅 활동 기록
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "활동기록",
                        fontFamily = inter_bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "플로깅 거리",
                                fontFamily = inter_bold,
                                fontSize = 16.sp,
                                color = GW_Gray100
                            )
                            Text(
                                text = String.format("%.2f",convertMetersToKilometers(viewModel.getTotalWalkingDistance()))+"Km",
                                fontFamily = inter_bold,
                                fontSize = 24.sp,
                                color = GW_Green100
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "보유 포인트",
                                fontFamily = inter_bold,
                                fontSize = 16.sp,
                                color = GW_Gray100
                            )
                            Text(
                                text = "${viewModel.getTotalPoint()} P",
                                fontFamily = inter_bold,
                                fontSize = 24.sp,
                                color = GW_Green100
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "주운 쓰레기",
                                fontFamily = inter_bold,
                                fontSize = 16.sp,
                                color = GW_Gray100
                            )
                            Text(
                                text = "${viewModel.getTotalTrashCount()} 개",
                                fontFamily = inter_bold,
                                fontSize = 24.sp,
                                color = GW_Green100
                            )
                        }
                    }
                }
                Divider(thickness = 20.dp, color = Color(0XFFF8F8F8))
                // 기부내역
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "기부내역",
                        fontFamily = inter_bold,
                        fontSize = 20.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "날짜",
                            fontFamily = inter_bold,
                            modifier = Modifier.weight(1f),

                        )
                        Text(
                            text = "기부처",
                            fontFamily = inter_bold,
                            modifier = Modifier.weight(2f)
                        )
                        Text(
                            text = "기부포인트",
                            fontFamily = inter_bold,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    for (donation in donationViewModel.getDonationList()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        ) {
                            // 입력 문자열을 LocalDateTime 객체로 파싱
                            val formatterInput = DateTimeFormatter.ISO_DATE_TIME
                            val dateTime = LocalDateTime.parse(donation.createDate, formatterInput)

                            // 원하는 형식으로 포맷팅
                            val formatterOutput = DateTimeFormatter.ofPattern("MM.dd")
                            val formattedDate = dateTime.format(formatterOutput)

                            Text(
                                text = formattedDate,
                                fontFamily = inter_regular,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )

                            Text(
                                text = donation.partnerName,
                                fontSize = 16.sp,
                                fontFamily = inter_regular,
                                modifier = Modifier.weight(2f)
                            )

                            Text(
                                text = "+"+donation.donationAmount.toString()+"P",
                                fontSize = 16.sp,
                                fontFamily = inter_regular,
                                color = GW_Green100,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    GreenWalkTheme {
        val navController = rememberNavController()
        MypageScreen(navController)
    }
}