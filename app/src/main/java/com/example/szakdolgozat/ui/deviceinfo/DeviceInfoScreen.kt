package com.example.szakdolgozat.ui.deviceinfo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DeviceInfoScreen() {
    val viewModel = hiltViewModel<DeviceInfoViewModel>()

    val deviceInfo by viewModel.deviceInfo.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (deviceInfo == null) {
                Text(text = "Nem sikerült lekérni az eszköz adatait!")
            }
            deviceInfo?.let {
                Text(text = "Brand: ${it.brand}")
                Text(text = "Model: ${it.model}")
                Text(text = "Name: ${it.name}")
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            ThermometerSensor()
        }
    }
}

@Composable
private fun ThermometerSensor() {
    val context = LocalContext.current

    val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val thermometer = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

    val temperature = remember {
        mutableFloatStateOf(0f)
    }

    val sensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

        }

        override fun onSensorChanged(event: SensorEvent) {
            Log.d("Temperature from sensor:", "${event.values[0]}")
            temperature.floatValue = event.values[0]
        }
    }

    sensorManager.registerListener(
        sensorEventListener,
        thermometer,
        SensorManager.SENSOR_DELAY_NORMAL
    )

    Text(text = "Hőmérséklet: ${temperature.floatValue}")

    sensorManager.unregisterListener(sensorEventListener)
}
