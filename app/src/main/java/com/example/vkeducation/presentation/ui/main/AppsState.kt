package com.example.vkeducation.presentation.ui.main

import androidx.compose.runtime.Immutable
import com.example.vkeducation.domain.model.AppItem

@Immutable
sealed interface AppsState {
    data object Error : AppsState
    data object Loading : AppsState
    data class Content(
        val apps: List<AppItem>,
    ) : AppsState
}
