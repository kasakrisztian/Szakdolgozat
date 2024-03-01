package com.example.szakdolgozat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.szakdolgozat.components.NavBarScaffold
import com.example.szakdolgozat.navigation.Navigation
import com.example.szakdolgozat.ui.theme.SzakdolgozatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SzakdolgozatTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavBarScaffold(onClick = { navController.navigate(it) }
                    ) {
                        Navigation(navController = navController,)
                    }
                }
            }
        }
    }
}
