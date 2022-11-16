package com.example.cursach.data.repository

import com.example.cursach.Article
import com.example.cursach.data.network.ArticlesService
import com.example.cursach.data.persistence.ArticlesDatabase
import com.example.cursach.data.toDomain
import com.example.cursach.data.toEntity
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val service: ArticlesService,
    private val db: ArticlesDatabase,
    private val connectivityManager: ConnectivityManager
) {

    suspend fun getArticles(): List<Article> {
        return try {
            when {
                connectivityManager.isConnected -> networkArticles()
                else -> cachedArticles()
            }
        } catch (e: Exception) {
            listOf() // todo result
        }
    }

    private suspend fun cachedArticles() = db.getArticles().map { it.toDomain() }

    private suspend fun networkArticles(): List<Article> {
        val response = service.getArticles()

        val entities = response.articles.map { it.toEntity() }
        db.saveArticles(entities)

        return response.articles.map { it.toDomain() }
    }
}
