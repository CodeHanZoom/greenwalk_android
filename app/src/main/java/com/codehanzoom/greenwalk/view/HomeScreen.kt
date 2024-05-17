package com.codehanzoom.greenwalk.view

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.MainActivity
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.compose.AttendanceArea
import com.codehanzoom.greenwalk.compose.DonationListArea
import com.codehanzoom.greenwalk.model.UserInfoResponseBody
import com.codehanzoom.greenwalk.nav.BottomNavigation
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.utils.RetrofitClient
import com.codehanzoom.greenwalk.utils.getGrade
import com.codehanzoom.greenwalk.viewModel.UserInfoViewModel
import retrofit2.Call
import retrofit2.Response

@Composable
fun  HomeScreen(navController: NavHostController) {
    val viewModel = UserInfoViewModel()
    // accessToken 저장
    var accessToken by remember { mutableStateOf(MainActivity.prefs.getString("accessToken", "")) }
    // userInfo 저장
    var userInfo by remember { mutableStateOf<UserInfoResponseBody?>(null) }

    LaunchedEffect(accessToken) {
        Log.d("test", accessToken)
        if (accessToken.isNotEmpty()) {
            RetrofitClient.instance.getUserInfo("Bearer $accessToken").enqueue(object : retrofit2.Callback<UserInfoResponseBody> {
                override fun onResponse(call: Call<UserInfoResponseBody>, response: Response<UserInfoResponseBody>) {
                    println(response.body()?.name)
                    if (response.isSuccessful) {
                        userInfo = response.body()
                        userInfo?.let {
                            userInfo ->
                                viewModel.setId(userInfo.id)
                                viewModel.setName(userInfo.name)
                                viewModel.setEmail(userInfo.email)
                                viewModel.setTotalPoint(userInfo.totalPoint)
                                viewModel.setTotalDonation(userInfo.totalDonation)
                                viewModel.setTotalStep(userInfo.totalStep)
                                viewModel.setTotalTrashCount(userInfo.totalTrashCount)
                                viewModel.setTotalWalkingDistance(userInfo.totalWalkingDistance)
                        }
                        Log.d("viewmodel test", viewModel.getName())
                        Log.d("HomeScreen", accessToken.toString())
                        Log.d("HomeScreen", "User Name: ${userInfo?.name}")
                        Log.d("HomeScreen", "Email: ${userInfo?.email}")
                    } else {
                        Log.e("HomeScreen", "Response Error : ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<UserInfoResponseBody>, t: Throwable) {
                    Log.e("HomeScreen", "Network Error : ${t.message}")
                }
            })
        }
    }

    Log.d("로그인", MainActivity.prefs.getString("accessToken", ""))
    Scaffold(
        topBar = {
            areaHeader()
        },
        bottomBar = {
            BottomNavigation(navController)
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
                    .fillMaxSize()
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                areaMyInfo(name = viewModel.getName(),
                    ploggingCount = viewModel.getTotalTrashCount(),
                    totalPoint = viewModel.getTotalPoint(),
                    totalWalkingDistance = viewModel.getTotalWalkingDistance(),
                    grade = getGrade(viewModel.getTotalPoint()))

                AttendanceArea()

                areaCheer(name = userInfo?.name)

                DonationListArea()
            }

        }
    }
           ploggingButton(navController)
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
            painter = painterResource(R.drawable.ic_notifications),
            contentDescription = "image for notification",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun areaMyInfo(name: String?="나희수",
               ploggingCount: Int?=-1,
               totalPoint: Int?=-1,
               totalWalkingDistance: Int?=-1,
               grade: String?="Bronze") {
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
            Text(name + "님")
            Text(ploggingCount.toString() + "개의 쓰레기를 플로깅 하셨어요")
            Spacer(modifier = Modifier.height(10.dp))
            Text("현재 " + name + " 님은 " + grade + "등급 입니다.")
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "$totalPoint P",
                color = Color("#8CB369".toColorInt()),
                fontWeight = FontWeight.Bold
            )
            Text("보유 포인트")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "$totalWalkingDistance Km",
                color = Color("#8CB369".toColorInt()),
                fontWeight = FontWeight.Bold
            )
            Text("플로깅 거리")
        }
    }
}

@Composable
fun areaCheer(name: String? = "나희수") {
    Text(
        "$name 님,\n플로깅해서 모은 포인트를 기부해보세요!", modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
fun ploggingButton(navController: NavHostController) {
    // 화면정보 불러오기
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    // x, y 좌표 설정
    // x: width 90%를 기준으로 좌,우 5% 가량 gap
    // y: height 80% 기준으로 설정
    val newX = (screenWidth.value * 0.1)/2
    val newY = screenHeight.value * 0.8
    Log.d("test", ""+screenWidth.value+" "+screenHeight.value )

    // 카메라 인텐트를 실행하기 위한 launcher
    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { isTaken ->
//        if (isTaken) {
//            // 이미지가 성공적으로 촬영되었을 때 동작할 내용을 작성합니다.
//            // 여기에서는 필요한 작업을 추가하시면 됩니다.
//        }
    }

    FloatingActionButton(
        onClick = {
//            // 카메라 인텐트 시작
            navController.navigate("PloggingScreen")
        },
        modifier = Modifier
            .width((screenWidth.value * 0.9).toInt().dp)
            .height(40.dp)
            .offset(newX.toInt().dp, newY.toInt().dp),
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
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}