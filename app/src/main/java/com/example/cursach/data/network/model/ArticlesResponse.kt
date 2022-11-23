package com.example.cursach.data.network.model

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("pagination") var paginationResponse: PaginationResponse? = PaginationResponse(),
    @SerializedName("data") var data: ArrayList<ArticlesDataResponse> = arrayListOf()
)

data class ArticlesDataResponse(
    @SerializedName("author") var author: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("source") var source: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("published_at") var publishedAt: String? = null
)

data class PaginationResponse(
    @SerializedName("limit") var limit: Int? = null,
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("total") var total: Int? = null
)