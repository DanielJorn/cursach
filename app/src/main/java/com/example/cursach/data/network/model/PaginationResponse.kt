package com.example.cursach.data.network.model

import com.google.gson.annotations.SerializedName


data class PaginationResponse(
    @SerializedName("limit") var limit: Int? = null,
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("total") var total: Int? = null
)