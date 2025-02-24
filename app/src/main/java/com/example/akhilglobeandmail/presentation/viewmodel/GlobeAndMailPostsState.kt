package com.example.akhilglobeandmail.presentation.viewmodel

import com.example.akhilglobeandmail.domain.model.GlobeAndMailPostInfo

data class GlobeAndMailPostsState(
    val isLoading: Boolean = false,
    val postsList: List<GlobeAndMailPostInfo> = emptyList(),
    val errorMessage: String = "",
)
