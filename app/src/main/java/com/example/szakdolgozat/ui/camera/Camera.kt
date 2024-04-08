package com.example.szakdolgozat.ui.camera

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.szakdolgozat.ui.camera.components.CameraPreview

@Composable
fun Camera() {
    val viewModel = hiltViewModel<CameraViewModel>()

    val isPermissionGranted by viewModel.isCameraPermissionGranted.collectAsState()
    val controller by viewModel.controller.collectAsState()
    val bitmap by viewModel.bitmap.collectAsState()
    val showCamera by viewModel.showCamera.collectAsState()

    // TODO - Szakdolgozatban bemutatni
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onPermissionGranted(isGranted)
        }
    )

    if (showCamera) {
        CameraPreview(
            controller = controller,
            onTakePhoto = viewModel::onTakePhoto,
            modifier = Modifier.fillMaxSize()
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.weight(1f)) {
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                    )
                }
            }
            Button(onClick = {
                // TODO - magyarázni
                if (!isPermissionGranted) {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
                viewModel.onShowCamera()
            }) {
                Text(text = "Kamera indítása")
            }
        }
    }
}
