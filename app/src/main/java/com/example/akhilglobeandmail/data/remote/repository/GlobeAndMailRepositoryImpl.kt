package com.example.akhilglobeandmail.data.remote.repository

import android.util.Log
import com.example.akhilglobeandmail.data.remote.GlobeAndMailApi
import com.example.akhilglobeandmail.data.remote.mapper.toPostInfo
import com.example.akhilglobeandmail.domain.model.GlobeAndMailResponse
import com.example.akhilglobeandmail.domain.repository.GlobeAndMailRepository
import javax.inject.Inject

class GlobeAndMailRepositoryImpl @Inject constructor(
    private val globeAndMailApi: GlobeAndMailApi
) : GlobeAndMailRepository {

    override suspend fun fetchPosts(): GlobeAndMailResponse {
        return try {
            val response = globeAndMailApi.fetchPosts()
            GlobeAndMailResponse.Success(
                posts = response.toPostInfo()
            )
        } catch (ex: Exception) {
            Log.e("GlobeAndMailRepository", "fetchGlobeAndMailPosts: ${ex.message}", ex)
            GlobeAndMailResponse.Error(errorMessage = ex.message, exception = ex)
        }
    }
}