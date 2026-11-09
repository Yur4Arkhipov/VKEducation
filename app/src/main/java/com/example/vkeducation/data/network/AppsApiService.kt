package com.example.vkeducation.data.network

import retrofit2.http.GET

interface AppsApiService {

    @GET("catalog")
    suspend fun getCatalog(): List<AppItemDto>
}
