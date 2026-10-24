package com.example.vkeducation.presentation.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.vkeducation.domain.model.Category
import com.example.vkeducation.presentation.ui.details.components.AppDetailsContent
import com.example.vkeducation.presentation.ui.details.components.AppDetailsError
import com.example.vkeducation.presentation.ui.details.components.AppDetailsLoading
import com.example.vkeducation.presentation.ui.details.model.AppDetails

@Composable
fun AppDetailsScreen(onBackClick: () -> Unit = {}, onStorefrontAppClick: () -> Unit = {}) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state = remember {
        AppDetailsState.Content(
            appDetails = AppDetails(
                id = "vk-app",
                name = "ВКонтакте",
                developer = "VK",
                category = Category.APP,
                ageRating = 12,
                size = 120f,
                iconUrl = "",
                screenshotUrlList = emptyList(),
                description = "Общайтесь, слушайте музыку и смотрите видео в одном приложении.",
            ),
            similarApps = emptyList(),
            descriptionCollapsed = false,
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { contentPadding ->
        AppDetailsContent(
            content = state,
            onBackClick = onBackClick,
            onShareClick = {

            },
            onInstallClick = {

            },
            onReadMoreClick = {

            },
            onSimilarAppDownloadClick = {

            },
            onStorefrontAppClick = onStorefrontAppClick,
            onDeveloperClick = {

            },
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .padding(contentPadding),
        )

//        when (val currentState = state) {
//            is AppDetailsState.Loading -> {
//                AppDetailsLoading(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .safeDrawingPadding()
//                        .padding(contentPadding),
//                )
//            }
//
//            is AppDetailsState.Error -> {
//                AppDetailsError(
//                    onRefreshClick = {},
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .safeDrawingPadding()
//                        .padding(contentPadding),
//                )
//            }

//            is AppDetailsState.Content -> {
//                AppDetailsContent(
//                    content = currentState,
//                    onBackClick = onBackClick,
//                    onShareClick = {
//
//                    },
//                    onInstallClick = {
//
//                    },
//                    onReadMoreClick = {
//
//                    },
//                    onSimilarAppDownloadClick = {
//
//                    },
//                    onStorefrontAppClick = onStorefrontAppClick,
//                    onDeveloperClick = {
//
//                    },
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .safeDrawingPadding()
//                        .padding(contentPadding),
//                )
//            }
//        }
    }
}
