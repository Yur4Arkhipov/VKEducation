package com.example.vkeducation.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.domain.usecase.GetAppsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppsViewModel @Inject constructor(
    private val getAppsUseCase: GetAppsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<AppsState>(AppsState.Loading)
    val uiState: StateFlow<AppsState> = _uiState.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            val apps = getAppsUseCase()

            _uiState.value = AppsState.Content(
                apps = apps
            )
        }
    }
}
