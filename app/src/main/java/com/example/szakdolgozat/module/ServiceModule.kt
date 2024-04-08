package com.example.szakdolgozat.module

import android.content.Context
import com.example.szakdolgozat.service.CameraService
import com.example.szakdolgozat.service.NotificationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideNotificationService(@ApplicationContext context: Context): NotificationService {
        return NotificationService(context)
    }

    @Provides
    @Singleton
    fun provideCameraService(@ApplicationContext context: Context): CameraService {
        return CameraService(context)
    }
}