package com.example.szakdolgozat

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import com.example.szakdolgozat.service.NotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Szakdolgozat : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    // TODO - Szakdolgozatban bemutatni
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationService.CHANNEL_ID,
            "hello_world",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        Log.d("Notification channel", "createad")
    }
}