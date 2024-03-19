package com.codehanzoom.greenwalk

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehanzoom.greenwalk.publicCompose.MaxWidthButton
import com.codehanzoom.greenwalk.publicCompose.TopBar
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun LoginActivity() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(title = "")
        Spacer(modifier = Modifier.height(60.dp))
        Logo()
        Spacer(modifier = Modifier.height(230.dp))
        CumstomTextField("이메일")
        Spacer(modifier = Modifier.height(40.dp))
        CumstomTextField("비밀번호")
        Spacer(modifier = Modifier.height(80.dp))
        MaxWidthButton(title = "로그인")
        Spacer(modifier = Modifier.height(20.dp))
        BottomContainer()
    }
}
@Composable
fun Logo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "GreenWalk",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = Color(0xFF8CB369),
        )
    }
}
@Composable
fun CumstomTextField(title: String) {
    var value by remember { mutableStateOf("") }

    Text(
        modifier = Modifier.padding(horizontal = 20.dp),
        text = title,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        color = Color(0xFF252525)
    )
    TextField(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = {value = it},
        keyboardOptions = if (title == "이메일"){
            KeyboardOptions(keyboardType = KeyboardType.Email)
        } else {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        },
        visualTransformation = if (title == "이메일"){
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        placeholder = { Text(text = title) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedPlaceholderColor = Color(0xFFBDBDBD),
            focusedPlaceholderColor = Color(0xFFBDBDBD),
            unfocusedIndicatorColor = Color(0xFFBDBDBD),
            focusedIndicatorColor = Color(0xFFBDBDBD)
        )
    )
}
@Composable
fun BottomContainer() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    ){
        Text(
            text = "아이디 찾기",
            fontWeight = FontWeight.W600,
            fontSize = 16.sp
        )
        Divider(
            color = Color(0xFF252525),
            modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(1.dp)
        )
        Text(
            text = "비밀번호 찾기",
            fontWeight = FontWeight.W600,
            fontSize = 16.sp
        )
        Divider(
            color = Color(0xFF252525),
            modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(1.dp)
        )
        Text(
            text = "회원가입",
            fontWeight = FontWeight.W600,
            fontSize = 16.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
fun LoginActivityPreview() {
    GreenWalkTheme {
        LoginActivity()
    }
}