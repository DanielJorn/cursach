package com.example.cursach.data.repository

import com.example.cursach.Article
import com.example.cursach.data.network.ArticlesService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val service: ArticlesService
) {

    suspend fun getArticles(): List<Article> {
        return try {
            service.getArticles().articles.map {
                Article(
                    it.title,
                    it.description ?: it.content,
                    it.urlToImage,
                    it.url,
                    it.source.name
                )
            }
        } catch (e: java.lang.Exception) {
            listOf()
        }
    }
}
