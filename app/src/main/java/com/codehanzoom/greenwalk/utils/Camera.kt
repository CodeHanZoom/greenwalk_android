//package com.codehanzoom.greenwalk.utils
//
//import android.content.ContentValues
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.media.Image
//import android.net.Uri
//import android.os.Build
//import android.provider.MediaStore
//import android.util.Log
//import android.widget.Toast
//import androidx.annotation.OptIn
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ExperimentalGetImage
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.ImageProxy
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.width
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.codehanzoom.greenwalk.MainActivity
//import com.codehanzoom.greenwalk.R
//import com.codehanzoom.greenwalk.compose.TopBar
//import com.codehanzoom.greenwalk.model.PloggingResponseBody
//import com.codehanzoom.greenwalk.ui.theme.GW_Black100
//import com.codehanzoom.greenwalk.viewModel.PloggingInfoViewModel
//import com.codehanzoom.greenwalk.viewModel.PloggingViewModel
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.OkHttpClient
//import okhttp3.RequestBody
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.io.ByteArrayOutputStream
//import java.io.FileNotFoundException
//import java.io.IOException
//import java.nio.ByteBuffer
//import java.util.concurrent.TimeUnit
//import kotlin.coroutines.resume
//import kotlin.coroutines.suspendCoroutine
//import androidx.compose.ui.tooling.preview.Preview as PreviewCompose
//
//@Composable
//fun CameraPreviewScreen(navController: NavHostController) {
//    val lensFacing = CameraSelector.LENS_FACING_BACK
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val context = LocalContext.current
//    val preview = Preview.Builder().build()
//    val previewView = remember {
//        PreviewView(context)
//    }
//    val cameraxSelector = CameraSelector.Builder()
//        .requireLensFacing(lensFacing)
//        .build()
//    val imageCapture = remember {
//        ImageCapture.Builder().build()
//    }
//    LaunchedEffect(lensFacing) {
//        val cameraProvider = context.getCameraProvider()
//        cameraProvider.unbind(preview)
//        cameraProvider.unbindAll()
//        cameraProvider.bindToLifecycle(lifecycleOwner, cameraxSelector, preview, imageCapture)
//        preview.setSurfaceProvider(previewView.surfaceProvider)
//    }
//    CameraUI(previewView, navController, imageCapture, context)
//}
//
//@Composable
//fun CameraUI(
//    previewView: PreviewView,
//    navController: NavHostController,
//    imageCapture: ImageCapture,
//    context: Context
//) {
//    // 클릭 이벤트 핸들러
//    val handleClick: () -> Unit = {
//        val text = "사진처리중 입니다!"
//        val duration = 30
//        // Navigation 처리
//        runBlocking {
//            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
//
//            captureImageAndSendToServer(
//                imageCapture,
//                context,
//                "http://aws-v5-beanstalk-env.eba-znduyhtv.ap-northeast-2.elasticbeanstalk.com/",
//                PloggingViewModel().getTotalStep(), // steps
//                PloggingViewModel().getTotalDistance() // distance
//            )
//
//            // 30초 delay
//            delay(40000)
//        }
//        navController.navigate("PointScreen") // 포인트 확인 및 기부할 목록을 확인할 페이지로 이동
//    }
//
//    // 화면정보 불러오기
//    val configuration = LocalConfiguration.current
//    val screenWidth = configuration.screenWidthDp.dp
//
//    // Compose 내부에서 사용할 수 있는 코루틴 스코프
//    val coroutineScope = rememberCoroutineScope()
//
//    TopBar(title = "사진촬영", navController = navController)
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .fillMaxSize()
//            .background(GW_Black100)
//
//    ) {
//        Spacer(modifier = Modifier.height(100.dp))
//        Box(
//            modifier = Modifier.width(screenWidth)
//
//        ) {
//            AndroidView(
//                { previewView },
//                modifier = Modifier
//                    .height(300.dp)
//                    .fillMaxWidth()
//            )
//        }
//
//        Spacer(modifier = Modifier.height(150.dp))
//        Image(
//            painter = painterResource(id = R.drawable.ic_camera),
//            contentDescription = null,
//            Modifier.clickable {
//                // 클릭 시 코루틴을 사용하여 비동기 작업 실행
//                coroutineScope.launch {
//                    Toast.makeText(context, "사진처리중 입니다!", Toast.LENGTH_LONG).show()
//
//                    // 비동기로 이미지 캡처 및 서버 전송
//                    captureImageAndSendToServer(
//                        imageCapture,
//                        context,
//                        "http://aws-v5-beanstalk-env.eba-znduyhtv.ap-northeast-2.elasticbeanstalk.com/",
//                        PloggingViewModel().getTotalStep(), // steps
//                        PloggingViewModel().getTotalDistance() // distance
//                    )
//
//                    // 20초 딜레이 후 화면 전환
//                    delay(20000)
//
//                    navController.navigate("PointScreen") // 포인트 확인 및 기부할 목록을 확인할 페이지로 이동
//                }
//                coroutineScope.launch {
//                    var remainTime = 20
//                    for (i in 1..4) {
//                        remainTime -= 5
//                        delay(5000)
//                        Toast.makeText(context, "$remainTime 초 남았습니다!", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//            }
//        )
//    }
//}
//
//private fun resizeBitmap(bitmap: Bitmap, targetWidth: Int, targetHeight: Int): Bitmap {
//    return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false)
//}
//
//private fun captureImageAndSendToServer(imageCapture: ImageCapture, context: Context, serverUrl: String, step: Int, walking: Double) {
//    // 저장명 설정
//    // System.currentTimeMillis()로 실시간 정보를 추가하여 고유 이름부여
//    val name = "CameraxImage_${System.currentTimeMillis()}.png"
//    val contentValues = ContentValues().apply {
//        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
//        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
//        }
//    }
//    val outputOptions = ImageCapture.OutputFileOptions.Builder(
//        context.contentResolver,
//        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//        contentValues
//    ).build()
//
//    val accessToken = MainActivity.prefs.getString("accessToken", "")
//    imageCapture.takePicture(
//        outputOptions,
//        ContextCompat.getMainExecutor(context),
//        object : ImageCapture.OnImageSavedCallback {
//            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                outputFileResults.savedUri?.let { uri ->
//                    sendImageToServer(context, uri, serverUrl, step, walking, "Bearer $accessToken")
//                    Log.d("imagecapture send", accessToken)
//                }
//            }
//
//            override fun onError(exception: ImageCaptureException) {
//                Log.e("CameraXApp", "Image save failed: $exception")
//            }
//        }
//    )
//}
//
//// timeout 적용
//private fun sendImageToServer(context: Context, imageUri: Uri, serverUrl: String, step: Int, walking: Double, accessToken: String) {
//    val mediaTypeTextPlain = "text/plain".toMediaTypeOrNull()
//    val viewModel = PloggingInfoViewModel()
//
//    try {
//        context.contentResolver.openInputStream(imageUri).use { inputStream ->
//            val bitmap = BitmapFactory.decodeStream(inputStream)
//            val resizedBitmap = resizeBitmap(bitmap, 640, 640)
//            val outputStream = ByteArrayOutputStream()
//            resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//            val imageBytes = outputStream.toByteArray()
//
//            val okHttpClient = OkHttpClient.Builder()
//                .readTimeout(20, TimeUnit.SECONDS)  // 읽기 타임아웃을 30초로 설정
//                .connectTimeout(20, TimeUnit.SECONDS)  // 연결 타임아웃을 30초로 설정
//                .build()
//
//            val retrofit = Retrofit.Builder()
//                .baseUrl(serverUrl)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            val service = retrofit.create(UploadService::class.java)
//            val mediaTypePng = "image/png".toMediaTypeOrNull()
//            val requestFile = RequestBody.create(mediaTypePng, imageBytes)
//            val imagePart = MultipartBody.Part.createFormData("image", "image.png", requestFile)
//            val stepBody = RequestBody.create(mediaTypeTextPlain, step.toString())
//            val walkingBody = RequestBody.create(mediaTypeTextPlain, walking.toString())
//
//            val call = service.uploadImage(imagePart, stepBody, walkingBody, "Bearer $accessToken")
//            call.enqueue(object : Callback<PloggingResponseBody> {
//                override fun onResponse(call: Call<PloggingResponseBody>, response: Response<PloggingResponseBody>) {
//                    if (response.isSuccessful) {
//                        val ploggingInfo = response.body()
//                        ploggingInfo?.let {
//                            body ->
//                            viewModel.setImageUrl(ploggingInfo.imageUrl?:"err")
//                            viewModel.setPoint(ploggingInfo.point?:-1)
//                            viewModel.setTrashCount(ploggingInfo.trashCount?:-1)
//                        }
//
//                        println("response: ${response.message()}, trashCount: ${viewModel.getTrashCount()}")
//                        println("Image uploaded successfully!")
//                    } else {
//                        println("Failed to upload image: ${response.code()}, error: ${response.errorBody()?.string()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<PloggingResponseBody>, t: Throwable) {
//                    println("Network error: ${t.message}")
//                }
//            })
//        }
//    } catch (e: FileNotFoundException) {
//        Log.e("CameraXApp", "File not found: $e")
//    } catch (e: IOException) {
//        Log.e("CameraXApp", "Error accessing file: $e")
//    }
//}
//
//// ImageProxy에서 이미지 데이터를 추출하여 byte 배열로 변환하는 확장 함수
//@OptIn(ExperimentalGetImage::class)
//private fun ImageProxy.toBytes(): ByteArray? {
//    val image: Image = this.image ?: return null
//    val buffer: ByteBuffer = image.planes[0].buffer
//    val bytes = ByteArray(buffer.remaining())
//    buffer.get(bytes)
//    return bytes
//}
//
//private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
//    suspendCoroutine { continuation ->
//        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
//            cameraProvider.addListener({
//                continuation.resume(cameraProvider.get())
//            }, ContextCompat.getMainExecutor(this))
//        }
//    }
//
//@PreviewCompose
//@Composable
//fun CameraPreview() {
//    val navController = rememberNavController()
//    CameraPreviewScreen(navController)
//}

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.codehanzoom.greenwalk.MainActivity
import com.codehanzoom.greenwalk.R
import com.codehanzoom.greenwalk.compose.TopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraPreviewScreen(navController: NavHostController) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val cameraxSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
    val imageCapture = remember { ImageCapture.Builder().build() }
    var isCapturing by remember { mutableStateOf(false) }
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraxSelector, preview, imageCapture)
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    CameraUI(previewView, navController, imageCapture, context, isCapturing, capturedImageUri) { capturing, uri ->
        isCapturing = capturing
        capturedImageUri = uri
    }
}

@Composable
fun CameraUI(
    previewView: PreviewView,
    navController: NavHostController,
    imageCapture: ImageCapture,
    context: Context,
    isCapturing: Boolean,
    capturedImageUri: Uri?,
    setCapturing: (Boolean, Uri?) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val coroutineScope = rememberCoroutineScope()

    TopBar(title = "사진촬영", navController = navController)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Box(modifier = Modifier.width(screenWidth)) {
            AndroidView({ previewView }, modifier = Modifier.height(300.dp).fillMaxWidth())
            if (isCapturing && capturedImageUri != null) {
                Image(
                    painter = rememberImagePainter(capturedImageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(150.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = null,
            Modifier.clickable {
                coroutineScope.launch {
                    Toast.makeText(context, "사진처리중 입니다!", Toast.LENGTH_LONG).show()
                    setCapturing(true, null)
                    captureImageAndSendToServer(
                        imageCapture,
                        context,
                        "http://aws-v5-beanstalk-env.eba-znduyhtv.ap-northeast-2.elasticbeanstalk.com/",
                        0, // steps
                        0.0 // distance
                    ) { uri ->
                        setCapturing(true, uri)
                    }
                    delay(20000)
                    navController.navigate("PointScreen")
                }
                coroutineScope.launch {
                    var remainTime = 20
                    for (i in 1..4) {
                        remainTime -= 5
                        delay(5000)
                        Toast.makeText(context, "$remainTime 초 남았습니다!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }
}

private fun captureImageAndSendToServer(
    imageCapture: ImageCapture,
    context: Context,
    serverUrl: String,
    step: Int,
    walking: Double,
    onImageCaptured: (Uri?) -> Unit
) {
    val name = "CameraxImage_${System.currentTimeMillis()}.png"
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }
    }
    val outputOptions = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    ).build()

    val accessToken = MainActivity.prefs.getString("accessToken", "")
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = outputFileResults.savedUri
                onImageCaptured(savedUri)
                savedUri?.let { uri ->
                    sendImageToServer(context, uri, serverUrl, step, walking, "Bearer $accessToken")
                    Log.d("imagecapture send", accessToken)
                }
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraXApp", "Image save failed: $exception")
                onImageCaptured(null)
            }
        }
    )
}

private fun sendImageToServer(
    context: Context,
    imageUri: Uri,
    serverUrl: String,
    step: Int,
    walking: Double,
    accessToken: String
) {
    // Send image to server logic here
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }
