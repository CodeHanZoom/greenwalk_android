package com.codehanzoom.greenwalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreenWalkTheme {
                Main()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBody() {
    GreenWalkTheme {
        Main()
    }

}