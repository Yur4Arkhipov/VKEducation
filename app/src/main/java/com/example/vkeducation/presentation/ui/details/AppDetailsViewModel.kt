package com.example.vkeducation.presentation.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.domain.model.Category
import com.example.vkeducation.presentation.ui.details.model.AppDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class AppDetailsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<AppDetailsState>(
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
    )
    val state = _state.asStateFlow()

    private var installJob: Job? = null

    fun onInstallClick() {
        val current = _state.value
        if (current !is AppDetailsState.Content) return

        if (current.installState is InstallState.Installing) {
            installJob?.cancel()
            installJob = null
            _state.value = current.copy(installState = InstallState.NotInstalled)
        } else {
            installJob = viewModelScope.launch {
                emulateInstall()
            }
        }
    }

    private suspend fun emulateInstall() {
        for (progress in 0..100 step 5) {
            delay(150.milliseconds)
            _state.value = (_state.value as AppDetailsState.Content).copy(
                installState = InstallState.Installing(progress)
            )
        }
        _state.value = (_state.value as AppDetailsState.Content).copy(
            installState = InstallState.Installed
        )
    }
}
