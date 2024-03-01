package com.example.szakdolgozat.ui.notification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.szakdolgozat.service.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationService: NotificationService,
    @ApplicationContext val applicationContext: Context
) : ViewModel() {

    private val _hasNotificationPermission = MutableStateFlow(true)
    val hasNotificationPermission = _hasNotificationPermission.asStateFlow()

    init {
        _hasNotificationPermission.value =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

            } else {
                true
            }
    }

    fun showNotification() {
        viewModelScope.launch {
            notificationService.showNotification()
        }
    }

    fun onPermissionGranted(isGranted: Boolean) {
        _hasNotificationPermission.value = isGranted
    }
}