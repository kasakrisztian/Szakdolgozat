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

    // TODO - nagyjából mindenről írni
    private val _isCameraPermissionGranted = MutableStateFlow(cameraService.hasCameraPermission())
    val isCameraPermissionGranted = _isCameraPermissionGranted.asStateFlow()

    private val _showCamera = MutableStateFlow(false)
    val showCamera = _showCamera.asStateFlow()

    private val _bitmap = MutableStateFlow<Bitmap?>(null)
    val bitmap = _bitmap.asStateFlow()

    private val _controller = MutableStateFlow(
        LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        })
    val controller = _controller.asStateFlow()

    fun onTakePhoto() {
        cameraService.takePhoto(_controller.value) {
            _bitmap.value = it
            _showCamera.value = false
        }
    }

    fun onPermissionGranted(isGranted: Boolean) {
        _isCameraPermissionGranted.value = isGranted
    }

    fun onShowCamera() {
        _showCamera.value = !_showCamera.value
    }
}