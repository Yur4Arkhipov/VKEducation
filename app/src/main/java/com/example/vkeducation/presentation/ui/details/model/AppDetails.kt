package com.example.vkeducation.presentation.ui.details.model

import com.example.vkeducation.domain.model.Category

data class AppDetails(
    val id: String,
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>?,
    val description: String,
)
