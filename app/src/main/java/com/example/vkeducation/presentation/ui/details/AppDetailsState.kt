package com.example.vkeducation.presentation.ui.details

import androidx.compose.runtime.Immutable
import com.example.vkeducation.presentation.ui.details.model.AppDetails
import com.example.vkeducation.presentation.ui.details.model.SimilarApp

@Immutable
sealed interface AppDetailsState {
    data object Error : AppDetailsState
    data object Loading : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val similarApps: List<SimilarApp>,
        val descriptionCollapsed: Boolean,
    ) : AppDetailsState
}