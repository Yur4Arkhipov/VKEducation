package com.example.vkeducation.data.network

import com.example.vkeducation.domain.model.AppDetails

data class AppDetailsDto(
    val id: String,
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

fun AppDetailsDto.toDomain() = AppDetails(
    id = id.hashCode(),
    name = name,
    description = description,
    category = category,
    iconUrl = iconUrl,
    developerName = developerName,
    developerUrl = developerUrl,
    screenshots = screenshots,
    version = version,
    downloads = downloads,
    rating = rating,
)
