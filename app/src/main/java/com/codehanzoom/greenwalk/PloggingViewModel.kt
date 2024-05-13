import android.util.Log
import androidx.lifecycle.ViewModel

class PloggingViewModel : ViewModel() {
    // 걸음수를 저장할 변수
    var totalSteps: Int = 0
        private set

    // 걸음수를 설정하는 메서드
    fun setTotalSteps(steps: Int) {
        totalSteps = steps
        Log.d("PloggingViewModel", "steps: $totalSteps")

    }

    fun getTotalStep(): Int {
        return totalSteps
    }
    var totalDistance: Double = 0.0
        private set

    fun setTotalDistance(distance: Double) {
        totalDistance = distance
        Log.d("PloggingViewModel", "distance: $totalDistance")

    }
}