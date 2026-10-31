package com.example.vkeducation.presentation.ui.main

import androidx.lifecycle.ViewModel
import com.example.vkeducation.data.apps
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppsViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow<AppsState>(AppsState.Loading)
    val uiState: StateFlow<AppsState> = _uiState.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        _uiState.value = AppsState.Content(
            apps = apps
        )
    }
}
