package com.example.szakdolgozat.service

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.szakdolgozat.R
import dagger.hilt.android.qualifiers.ApplicationContext

class NotificationService(
    @ApplicationContext private val context: Context
) {
    companion object {
        const val CHANNEL_ID = "notification_channel"
    }

    // TODO - Szakdolgozatban magyarázni
    fun showNotification() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentText("Új értesítés")
            .setContentTitle("Hello World!")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .build()
        notificationManager.notify(1, notification)
    }
}