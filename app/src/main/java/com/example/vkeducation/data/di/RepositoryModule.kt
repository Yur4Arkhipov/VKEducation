package com.example.vkeducation.data.di

import com.example.vkeducation.data.repository.AppsRepository
import com.example.vkeducation.data.repository.AppsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    internal abstract fun bindsAppRepository(
        impl: AppsRepositoryImpl,
    ): AppsRepository
}
