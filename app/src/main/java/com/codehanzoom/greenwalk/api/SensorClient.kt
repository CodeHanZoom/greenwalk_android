package com.codehanzoom.greenwalk.api

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

class SensorClient(private val context: Context, private val listener: StepCountListener) : SensorEventListener {

    interface StepCountListener {
        fun onStepCountChanged(stepCount: Int)
    }

    private lateinit var sensorManager: SensorManager
    private var stepCountSensor: Sensor? = null

    // 현재 걸음 수
    private var currentSteps = 0

    @RequiresApi(api = Build.VERSION_CODES.Q)
    fun initialize() {
        // 활동 퍼미션 체크
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
//            Toast.makeText(context, "Activity recognition permission denied", Toast.LENGTH_SHORT).show()
//            return
//        }

        // 걸음 센서 연결
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        // 디바이스에 걸음 센서의 존재 여부 체크
        if (stepCountSensor == null) {
            Toast.makeText(context, "No Step Sensor", Toast.LENGTH_SHORT).show()
        }
    }

    fun start() {
        stepCountSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        // 걸음 센서 이벤트 발생시
        if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0f) {
                // 센서 이벤트가 발생할 때마다 걸음수 증가
                currentSteps++
                listener.onStepCountChanged(currentSteps)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // 센서 정확도 변경 시
    }
}
