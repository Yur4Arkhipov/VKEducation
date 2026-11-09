package com.example.vkeducation.data.repository

import com.example.vkeducation.domain.model.AppItem

interface AppsRepository {
    suspend fun getApps(): List<AppItem>
}
