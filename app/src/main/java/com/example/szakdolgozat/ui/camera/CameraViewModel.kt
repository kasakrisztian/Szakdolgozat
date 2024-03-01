package com.example.szakdolgozat.ui.camera

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import com.example.szakdolgozat.service.CameraService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraService: CameraService,
    @ApplicationContext val applicationContext: Context
) : ViewModel() {

    private val _isCameraPermissionGranted = MutableStateFlow(cameraService.hasCameraPermission())
    val isCameraPermissionGranted = _isCameraPermissionGranted.asStateFlow()

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    private val _controller = MutableStateFlow(
        LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        })
    val controller = _controller.asStateFlow()

    fun onTakePhoto() {
        cameraService.takePhoto(_controller.value) { _bitmaps.value += it }
    }

    fun onPermissionGranted(isGranted: Boolean) {
        _isCameraPermissionGranted.value = isGranted
    }
}