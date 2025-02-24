package com.example.akhilglobeandmail.di

import com.example.akhilglobeandmail.data.remote.repository.GlobeAndMailRepositoryImpl
import com.example.akhilglobeandmail.domain.repository.GlobeAndMailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGlobeAndMailRepository(defaultImpl: GlobeAndMailRepositoryImpl): GlobeAndMailRepository
}