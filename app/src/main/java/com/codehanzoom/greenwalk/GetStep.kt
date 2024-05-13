import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.codehanzoom.greenwalk.ui.theme.zenDots
import com.codehanzoom.greenwalk.SensorClient
import kotlinx.coroutines.coroutineScope

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun GetStep(viewModel: PloggingViewModel) {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val sensorClient = SensorClient(context, object : SensorClient.StepCountListener {
            override fun onStepCountChanged(stepCount: Int) {
                viewModel.setTotalSteps(stepCount)
            }
        })
        sensorClient.initialize()
        sensorClient.start()
        onDispose {
            sensorClient.stop()
        }
    }

    Text(
        text = "${viewModel.getTotalStep()} stpes",
        fontFamily = zenDots,
        color = Color.White,
        fontSize = 34.sp,
        )
}


