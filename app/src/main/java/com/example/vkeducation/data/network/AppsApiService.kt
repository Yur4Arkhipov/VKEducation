package com.example.vkeducation.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface AppsApiService {

    @GET("catalog")
    suspend fun getCatalog(): List<AppItemDto>

    @GET("{id}")
    suspend fun getAppDetails(
        @Path("id") id: String
    ): AppDetailsDto
}
