package com.example.cursach.data.network.model

import com.google.gson.annotations.SerializedName


data class ArticlesResponse(
    @SerializedName("pagination") var paginationResponse: PaginationResponse? = PaginationResponse(),
    @SerializedName("data") var data: ArrayList<ArticlesDataResponse> = arrayListOf()
)