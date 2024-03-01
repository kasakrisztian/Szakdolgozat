package com.example.szakdolgozat.service

import android.content.Context
import android.os.Build
import com.example.szakdolgozat.data.DeviceInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeviceInfoService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getDeviceInfo(): Flow<DeviceInfo?> = flow{
        val deviceInfo = try {
            val model = Build.MODEL
            val brand = Build.BRAND
            val name = Build.DEVICE

            DeviceInfo(
                model = model,
                brand = brand,
                name = name
            )
        } catch (e: Exception) {
            null
        }

        emit(deviceInfo)
    }
}