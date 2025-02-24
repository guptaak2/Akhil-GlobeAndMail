package com.example.akhilglobeandmail.data.remote.mapper

import com.example.akhilglobeandmail.data.remote.GlobeAndMailResponseDto
import com.example.akhilglobeandmail.domain.model.GlobeAndMailPostInfo
import com.example.akhilglobeandmail.domain.model.GlobeAndMailPostsInfo

fun GlobeAndMailResponseDto.toPostInfo() = GlobeAndMailPostsInfo(
    posts = buildList {
        recommendations?.map { post ->
            add(
                GlobeAndMailPostInfo(
                    image = post.promoImage.urls.url.orEmpty(),
                    title = post.title?.trim().orEmpty(),
                    body = post.deck.orEmpty(),
                    authors = formatAuthors(post.byline?.first()).uppercase(),
                    isProtectionProduct = post.protectionProduct == "red"
                )
            )
        } ?: emptyList<GlobeAndMailPostInfo>()
    }
)

private fun formatAuthors(authors: String?): String {
    if (authors.isNullOrEmpty()) return ""
    val authorsList = authors.trim().split(",")
    return when (authorsList.size) {
        1 -> authorsList.first()
        2 -> "${authorsList.first()} and ${authorsList.last()}"
        else -> "${authorsList.dropLast(1).joinToString(", ")} and ${authorsList.last()}"
    }
}