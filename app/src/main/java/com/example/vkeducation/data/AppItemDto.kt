package com.example.vkeducation.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.vkeducation.domain.model.AppItem

data class AppItemDto(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val category: Int,
    @DrawableRes val icon: Int,
)


fun AppItemDto.toDomain() = AppItem(
    id = id,
    name = name,
    description = description,
    category = category,
    icon = icon
)
