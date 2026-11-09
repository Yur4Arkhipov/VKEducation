package com.example.vkeducation.data.repository

import com.example.vkeducation.data.network.AppsApiService
import com.example.vkeducation.data.network.toDomain
import com.example.vkeducation.domain.model.AppItem
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val apiService: AppsApiService
) : AppsRepository {

    override suspend fun getApps(): List<AppItem> {
        return apiService.getCatalog().map { it.toDomain() }
    }
}
