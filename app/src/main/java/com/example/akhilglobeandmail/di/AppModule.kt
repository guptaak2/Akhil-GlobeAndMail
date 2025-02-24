package com.example.akhilglobeandmail.di

import com.example.akhilglobeandmail.data.remote.GlobeAndMailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): GlobeAndMailApi = Retrofit.Builder()
        .baseUrl(GlobeAndMailApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GlobeAndMailApi::class.java)
}