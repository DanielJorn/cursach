package com.example.cursach.data.network.model

import com.google.gson.annotations.SerializedName

data class TranslationResponse(
    @SerializedName("data") var data: Data? = Data()
)

data class Translations(
    @SerializedName("translatedText") var translatedText: String? = null,
    @SerializedName("detectedSourceLanguage") var detectedSourceLanguage: String? = null
)

data class Data(
    @SerializedName("translations") var translations: ArrayList<Translations> = arrayListOf()
)