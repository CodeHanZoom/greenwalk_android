
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.compose.LargeButton
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun StartScreen(navController: NavHostController) {
    Image(
        painter = painterResource(R.drawable.img_splash),
        contentDescription = "배경화면",
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Spacer(modifier = Modifier.height(500.dp))

        LargeButton(title = "시작하기") {
            navController.navigate("LoginScreen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun startScreenPreivew() {
    GreenWalkTheme {
        val navController = rememberNavController()
        StartScreen(navController = navController)
    }
}