package com.codehanzoom.greenwalk.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehanzoom.greenwalk.ui.theme.GreenWalkTheme

@Composable
fun DonationListArea() {
    Column {
        Card (
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Picture")
                    Column(
                        modifier = Modifier.width(50.dp)
                    ) {
                        Text("기부처")
                        Text("500P")
                    }

                    SmallButton("기부")
                }
            }
        }
        for (i: Int in 1..8) {
            Card (
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Picture")
                        Column(
                            modifier = Modifier.width(50.dp)
                        ) {
                            Text("기부처")
                            Text("500P")
                        }

                        SmallButton("기부")
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewDonationListArea() {
    GreenWalkTheme {
        DonationListArea()
    }
}