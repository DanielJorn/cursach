package com.example.cursach.data.network

import com.example.cursach.data.network.model.ArticlesResponse
import retrofit2.http.GET

interface ArticlesService {

    @GET("http://api.mediastack.com/v1/news?access_key=bf0a5dd9d7a7f7068c8dda97fe06f071")
    suspend fun getArticles(): ArticlesResponse
}