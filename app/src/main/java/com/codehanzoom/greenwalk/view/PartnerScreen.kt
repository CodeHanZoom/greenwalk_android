import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.codehanzoom.greenwalk.compose.SmallButton
import com.codehanzoom.greenwalk.model.PartnersResponseBody
import com.codehanzoom.greenwalk.ui.theme.GW_Black100
import com.codehanzoom.greenwalk.ui.theme.GW_Yellow100
import com.codehanzoom.greenwalk.ui.theme.inter_bold
import com.codehanzoom.greenwalk.viewModel.DonationViewModel
import com.codehanzoom.greenwalk.viewModel.PartnersViewModel

@Composable
fun PartnersListScreen() {
    val viewModel: PartnersViewModel = viewModel()

    val partners by viewModel.partners.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        PartnersList(partners)
    }
}

@Composable
fun PartnersList(partners: List<PartnersResponseBody>) {
    LazyColumn {
        items(partners) { partners ->
            PartnersItem(partners)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PartnersItem(partner: PartnersResponseBody) {
    val viewModel = DonationViewModel() // DonationViewModel 초기화
    var bottomSheetVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Image URL을 이용하여 이미지 표시
                Image(
                    painter = rememberAsyncImagePainter(model = partner.imageUrl),
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(40.dp),
                    contentScale = ContentScale.Crop
                )

                Column {
                    Text(
                        text = partner.name,
                        fontFamily = inter_bold,
                        color = GW_Black100,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "지금까지 모인 포인트 ${partner.totalDonationAmount}P",
                        fontFamily = inter_bold,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
                SmallButton(title = "기부하기") { bottomSheetVisible = true }
            }
            if (bottomSheetVisible) {
                BottomSheetContent(
                    partnerId = partner.id,
                    partnerName = partner.name,
                    onDismiss = { bottomSheetVisible = false },
                    onConfirm = { amount ->
                        // 여기에서 amount를 사용하여 기부 액션을 수행합니다.
//                        Log.d("Donation", "기부처: ${partner.name}, 금액: $amount")
                        viewModel.fetchDonate(context,partner.id, amount) // DonationViewModel의 donate 호출
                        bottomSheetVisible = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(
    partnerId: Int,
    partnerName: String,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var sendPoint by remember { mutableStateOf("") }

    ModalBottomSheetLayout(
        sheetState = ModalBottomSheetState(ModalBottomSheetValue.Expanded),
        sheetContent = {},
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$partnerName 님에게",
                    fontFamily = inter_bold,
                    fontSize = 18.sp,
                    color = GW_Yellow100,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "얼마를 기부하시겠어요?",
                    fontSize = 18.sp,
                    color = GW_Black100,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = sendPoint,
                    onValueChange = { sendPoint = it },
                    label = { Text("금액 입력") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                    keyboardActions = KeyboardActions(onDone = {
//                        // 키보드의 "완료" 버튼을 누르면 기부 액션을 수행합니다.
//                        if (donationAmount.isNotEmpty()) {
//                            onConfirm(donationAmount.toInt())
//                        }
//                        onDismiss()
//                    })
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SmallButton(title = "취소") { onDismiss() }
                    SmallButton(title = "확인") {
                        if (sendPoint.isNotEmpty()) {
                            onConfirm(sendPoint.toInt())
//                            DonationViewModel().donate(partnerId, sendPoint.toInt())
                        }
                        onDismiss()
                    }
                }
            }
        }
    )
}