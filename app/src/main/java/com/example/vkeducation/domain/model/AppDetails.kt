package com.example.vkeducation.domain.model

data class AppDetails(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
    val developerName: String,
    val developerUrl: String,
    val screenshots: List<String>,
    val version: String,
    val downloads: Long,
    val rating: Float,
)
