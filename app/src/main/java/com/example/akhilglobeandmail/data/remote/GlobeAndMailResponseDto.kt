package com.example.akhilglobeandmail.data.remote

import com.google.gson.annotations.SerializedName

data class GlobeAndMailResponseDto(
    @SerializedName("request_id")
    val requestId: String?,
    @SerializedName("widget_id")
    val widgetId: String?,
    val recommendations: List<GlobeAndMailPostDto>?
)

data class GlobeAndMailPostDto(
    val title: String?,
    val deck: String?,
    val byline: List<String>?,
    val category: String?,
    @SerializedName("protection_product")
    val protectionProduct: String?,
    @SerializedName("promo_image")
    val promoImage: PromoImageDto,
)

data class PromoImageDto(
    val urls: PromoImageUrlsDto
)

data class PromoImageUrlsDto(
    @SerializedName("650")
    val url: String?
)
