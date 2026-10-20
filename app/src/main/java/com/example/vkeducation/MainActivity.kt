package com.example.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vkeducation.presentation.theme.VKEducationTheme
import com.example.vkeducation.presentation.ui.AppsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme(dynamicColor = false) {
                AppsScreen()
            }
        }
    }
}
