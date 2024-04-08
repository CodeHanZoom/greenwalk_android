package com.codehanzoom.greenwalk.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.ui.theme.GW_Black100
import com.codehanzoom.greenwalk.ui.theme.GW_Typography
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun TopBar(title: String, modifier: Modifier? = null, navController: NavHostController) {
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "Back",
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = GW_Typography.titleSmall,
            color = GW_Black100,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    GreenWalkTheme {
        val navController = rememberNavController()
        TopBar("회원가입", navController = navController)
    }
}