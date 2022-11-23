package com.example.cursach.data.network

import com.example.cursach.data.network.model.ArticlesResponse
import com.example.cursach.data.network.model.TranslationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesService {

    @GET("http://api.mediastack.com/v1/news?access_key=bf0a5dd9d7a7f7068c8dda97fe06f071")
    suspend fun getArticles(): ArticlesResponse

    @GET("https://translation.googleapis.com/language/translate/v2?key=AIzaSyCsBgpQAJcZBucwf_sfw5hfJ0TAQIqAJTE&target=uk")
    suspend fun translate(@Query("q") query: String): TranslationResponse
}