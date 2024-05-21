package com.codehanzoom.greenwalk.view

import android.text.BoringLayout
import android.util.Patterns
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
import androidx.compose.ui.Alignment
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
import com.codehanzoom.greenwalk.model.LoginRequestBody
import com.codehanzoom.greenwalk.model.SignUpRequestBody
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.GW_Red200
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme
import com.codehanzoom.greenwalk.viewModel.LoginViewModel
import com.codehanzoom.greenwalk.viewModel.SignupViewModel
import java.util.regex.Pattern

@Composable
fun SignUpScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isConfirmPasswordValid by remember { mutableStateOf(true) }
    var isPasswordMatching by remember { mutableStateOf(true) }
    var isEmpty by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    isEmailValid = isEmailValid(email)
    isPasswordValid = isPasswordValid(password)
    isConfirmPasswordValid = isPasswordValid(confirmPassword)


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(title = "회원가입", navController = navController)
        Divider(thickness = 0.5.dp, color = Color.Gray)
        Spacer(modifier = Modifier.height(30.dp))
        SignUpTextField(
            title = "이메일",
            value = email,
            onValueChange = { email = it }
        )
        // 이메일 유효성 검사
        if (!isEmailValid && email.isNotEmpty()) {
            Text(
                text = "이메일이 유효하지 않습니다.",
                color = GW_Red200,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        SignUpTextField(
            title = "비밀번호",
            value = password,
            onValueChange = { password = it }
        )
        // 비밀번호 유효성 검사
        if (!isPasswordValid && password.isNotEmpty()) {
            Text(
                text = "비밀번호가 유효하지 않습니다.",
                color = GW_Red200,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        SignUpTextField(
            title = "비밀번호 확인",
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                isPasswordMatching = password == it
            }
        )
        // 비밀번호 확인 유효성 검사
        if(!isConfirmPasswordValid && confirmPassword.isNotEmpty() || !isPasswordMatching) {
            Text(
                text = "비밀번호가 유효하지 않습니다.",
                color = Color.Red,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        SignUpTextField(
            title = "이름",
            value = name,
            onValueChange = { name = it }
        )
//        Spacer(modifier = Modifier.height(50.dp))
        Column(
            Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .height(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isEmpty) {
                Text(
                    text = errorMessage,
                    color = GW_Red200,
                )
            }
        }
//        Spacer(modifier = Modifier.height(110.dp))
//        MaxWidthButton(title = "가입하기") {
//            // 공백 확인
//            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty()) {
//                isEmpty = true
//                errorMessage = "모든 항목을 입력해 주세요."
//            } else {
//                isEmpty = false
//                errorMessage = ""
//
//                if(isEmailValid && isPasswordValid && isConfirmPasswordValid) {
//                    val userData = SignUpRequestBody(email, password, name)
//                    SignupViewModel(userData).retrofitWork(navController = navController)
//                }
//            }
//        }
    }
    SignUpButton(
        navController,
        email, password,
        confirmPassword,
        name,
        onErrorMessageChange = { errorMessage = it; isEmpty = it.isNotEmpty() },
        isEmailValid,
        isPasswordValid,
        isConfirmPasswordValid
    )
}


@Composable
fun SignUpTextField(title: String, value: String, onValueChange: (String) -> Unit) {

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
        onValueChange = onValueChange,
        keyboardOptions =
        when (title) {
            "이메일" -> KeyboardOptions(keyboardType = KeyboardType.Email)
            "비밀번호" -> KeyboardOptions(keyboardType = KeyboardType.Password)
            "비밀번호 확인" -> KeyboardOptions(keyboardType = KeyboardType.Password)
            else -> { KeyboardOptions(keyboardType = KeyboardType.Text)}
        },
        visualTransformation =
        when (title) {
            "이메일" -> VisualTransformation.None
            "비밀번호" -> PasswordVisualTransformation()
            "비밀번호 확인" -> PasswordVisualTransformation()
            else -> {VisualTransformation.None}
        },
        placeholder = {
            when (title) {
                "이메일" -> Text(text = "이메일을 입력해 주세요")
                "비밀번호" -> Text(text = "비밀번호를 입력해 주세요")
                "비밀번호 확인" -> Text(text = "비밀번호를 입력해 주세요")
                else -> Text(text = "이름을 입력해 주세요")
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.Black,
            focusedPlaceholderColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            focusedIndicatorColor = GW_Green100,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}
// 이메일 유효성 검사 메소드
fun isEmailValid(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

// 비밀번호 유효성 검사 메소드
fun isPasswordValid(password: String): Boolean {
    val pattern = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&+=])(?=\\S+\$).{8,}\$")
    return pattern.matcher(password).matches()
}

@Composable
fun SignUpButton(
    navController: NavHostController,
    email: String,
    password: String,
    confirmPassword: String,
    name: String,
    onErrorMessageChange: (String) -> Unit,
    isEmailValid: Boolean,
    isPasswordValid: Boolean,
    isConfirmPasswordValid: Boolean
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        MaxWidthButton(title = "가입하기") {
            // 공백 확인
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty()) {
                onErrorMessageChange("모든 항목을 입력해 주세요.")
            } else {
                onErrorMessageChange("")
                if(isEmailValid && isPasswordValid && isConfirmPasswordValid) {
                    val userData = SignUpRequestBody(email, password, name)
                    SignupViewModel(userData).retrofitWork(navController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterActivityPreview() {
    GreenWalkTheme {
        val navController = rememberNavController()
        SignUpScreen(navController = navController)
    }
}