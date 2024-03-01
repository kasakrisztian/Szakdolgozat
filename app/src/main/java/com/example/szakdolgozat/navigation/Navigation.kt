package com.example.szakdolgozat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.szakdolgozat.data.BottomNavItem
import com.example.szakdolgozat.ui.camera.Camera
import com.example.szakdolgozat.ui.deviceinfo.DeviceInfoScreen
import com.example.szakdolgozat.ui.notification.NotificationScreen
import com.example.szakdolgozat.ui.photopicker.PhotoPickerScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Notification.route
    ) {
        composable(
            route = BottomNavItem.Notification.route
        ) {
            NotificationScreen()
        }
        composable(
            route = BottomNavItem.PhotoPicker.route
        ) {
            PhotoPickerScreen()
        }
        composable(
            route = BottomNavItem.Camera.route
        ) {
            Camera()
        }
        composable(
            route = BottomNavItem.Device.route
        ) {
            DeviceInfoScreen()
        }
    }
}