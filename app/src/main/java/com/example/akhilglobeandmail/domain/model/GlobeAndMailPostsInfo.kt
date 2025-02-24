package com.example.akhilglobeandmail.domain.model

data class GlobeAndMailPostsInfo(
    val posts: List<GlobeAndMailPostInfo>
)

data class GlobeAndMailPostInfo(
    val image: String,
    val title: String,
    val body: String,
    val authors: String,
    val isProtectionProduct: Boolean,
)


