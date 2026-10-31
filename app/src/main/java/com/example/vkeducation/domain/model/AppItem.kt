package com.example.vkeducation.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AppItem(
    val id: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val category: Int,
    @DrawableRes val icon: Int,
)
