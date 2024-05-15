import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codehanzoom.greenwalk.MainActivity
import com.codehanzoom.greenwalk.model.PartnersResponseBody
import com.codehanzoom.greenwalk.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartnersViewModel : ViewModel() {
    val TAG = "PartnersViewModel"

    private val _partnersResponse = MutableLiveData<List<PartnersResponseBody>?>()
    val partnersResponse: MutableLiveData<List<PartnersResponseBody>?> get() = _partnersResponse

    init {
        val accessToken = MainActivity.prefs.getString("accessToken", "")

        RetrofitClient.getPartnersApi.getPartners("Bearer $accessToken")
            .enqueue(object : Callback<List<PartnersResponseBody>> {
                override fun onResponse(
                    call: Call<List<PartnersResponseBody>>,
                    response: Response<List<PartnersResponseBody>>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: success")
                        val partnersResponse = response.body()
                        _partnersResponse.value = partnersResponse
                    } else {
                        // 서버 에러 처리
                         Log.d(TAG, "onResponse Error: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<List<PartnersResponseBody>>, t: Throwable) {
                    // 네트워크 에러 처리
                     Log.d(TAG, "onFailure: ${t.message}")
                }
            })
    }
}
