package com.example.vkeducation.data.network

import com.example.vkeducation.domain.model.AppItem

data class AppItemDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
)

fun AppItemDto.toDomain() = AppItem(
    id = id.hashCode(),
    name = name,
    description = description,
    category = category,
    iconUrl = iconUrl,
)
