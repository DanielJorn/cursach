package com.example.cursach.data.network

import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResults") var totalResults: Int? = null,
    @SerializedName("articles") var articles: ArrayList<Article> = arrayListOf()
)