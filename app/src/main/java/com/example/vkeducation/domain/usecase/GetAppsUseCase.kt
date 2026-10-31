package com.example.vkeducation.domain.usecase

import com.example.vkeducation.data.repository.AppsRepository
import com.example.vkeducation.domain.model.AppItem
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    suspend operator fun invoke(): List<AppItem> {
        return repository.getApps()
    }
}