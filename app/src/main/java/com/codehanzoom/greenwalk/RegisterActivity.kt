package com.codehanzoom.greenwalk

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codehanzoom.greenwalk.compose.MaxWidthButton
import com.codehanzoom.greenwalk.compose.TopBar
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun RegisterActivity(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(title = "회원가입", navController = navController)
        Divider()
        Spacer(modifier = Modifier.height(30.dp))
        CustomTextField("이메일")
        Spacer(modifier = Modifier.height(30.dp))
        CustomTextField("비밀번호")
        Spacer(modifier = Modifier.height(30.dp))
        CustomTextField("비밀번호 확인")
        Spacer(modifier = Modifier.height(30.dp))
        CustomTextField("이름")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 700.dp)
    ) {
        MaxWidthButton(title = "가입하기") {

        }
    }
}


@Composable
fun CustomTextField(title: String) {
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
        onValueChange = { value = it },
        keyboardOptions = if (title == "이메일") {
            KeyboardOptions(keyboardType = KeyboardType.Email)
        } else {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        },
        visualTransformation = if (title == "이메일") {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        placeholder = {
            if (title == "이메일") {
                Text(text = "이메일을 입력해 주세요")
            } else if(title == "비밀번호") {
                Text(text = "비밀번호를 입력해 주세요")
            } else if (title == "비밀번호 확인"){
                Text(text = "비밀번호를 입력해 주세요")
            } else {
                Text(text = "이름을 입력해 주세요")
            }
        },
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

@Preview(showBackground = true)
@Composable
fun RegisterActivityPreview() {
    GreenWalkTheme {
        val navController = rememberNavController()
        RegisterActivity(navController)
    }
}