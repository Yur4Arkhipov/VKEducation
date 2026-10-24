package com.example.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkeducation.presentation.theme.VKEducationTheme
import com.example.vkeducation.presentation.ui.details.AppDetailsScreen
import com.example.vkeducation.presentation.ui.main.AppsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme(dynamicColor = false) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "apps") {
                    composable("apps") {
                        AppsScreen(onAppClick = { navController.navigate("details") })
                    }
                    composable("details") {
                        AppDetailsScreen(
                            onBackClick = { navController.popBackStack() },
                            onStorefrontAppClick = { navController.popBackStack("apps", false) },
                        )
                    }
                }
            }
        }
    }
}
