package com.example.akhilglobeandmail.domain.repository

import com.example.akhilglobeandmail.domain.model.GlobeAndMailResponse

interface GlobeAndMailRepository {

    suspend fun fetchPosts(): GlobeAndMailResponse
}