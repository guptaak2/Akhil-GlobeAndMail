package com.example.akhilglobeandmail.domain.model

sealed interface GlobeAndMailResponse {

    data class Success(val posts: GlobeAndMailPostsInfo): GlobeAndMailResponse

    data class Error(val errorMessage: String?, val exception: Exception?): GlobeAndMailResponse
}