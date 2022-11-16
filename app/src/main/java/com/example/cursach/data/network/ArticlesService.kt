package com.example.cursach.data.network

import com.example.cursach.data.network.model.ArticlesResponse
import retrofit2.http.GET

interface ArticlesService {

    @GET("https://newsapi.org/v2/top-headlines?country=us&apiKey=83fcda354365424e955b75357a92fbb5")
    suspend fun getArticles(): ArticlesResponse
}