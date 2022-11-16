package com.example.cursach.data.network.model

import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String
)