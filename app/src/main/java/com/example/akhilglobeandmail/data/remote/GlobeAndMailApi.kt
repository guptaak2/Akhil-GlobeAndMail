package com.example.akhilglobeandmail.data.remote

import retrofit2.http.GET

interface GlobeAndMailApi {

    @GET("trending_and_sophi/recommendations.json")
    suspend fun fetchPosts(): GlobeAndMailResponseDto

    companion object {
        const val BASE_URL = "https://d2c9087llvttmg.cloudfront.net/"
    }
}