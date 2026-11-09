package com.example.vkeducation.data.repository

import com.example.vkeducation.data.apps
import com.example.vkeducation.data.toDomain
import com.example.vkeducation.domain.model.AppItem
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(): AppsRepository {
    override suspend fun getApps(): List<AppItem> {
        return apps.map { it.toDomain() }
    }
}
