package com.codehanzoom.greenwalk.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehanzoom.greenwalk.ui.theme.GW_Green100
import com.codehanzoom.greenwalk.ui.theme.GW_Typography
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun SmallButton(title: String, onClick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GW_Green100
        ),
        modifier = Modifier
            .size(80.dp, 40.dp),
        onClick = onClick) {
        Text(
            text = title,
            style = GW_Typography.labelSmall,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SmallButtonPreview() {
    GreenWalkTheme {
//        SmallButton("기부하기", )
    }
}