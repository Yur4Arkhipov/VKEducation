package com.example.vkeducation.presentation.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class AppDetailsViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    init {
        getAppDetails()
    }

    fun getAppDetails() {

    }
}