package com.example.cursach.data.repository

import com.example.cursach.Article
import com.example.cursach.Failure
import com.example.cursach.OpResult
import com.example.cursach.Success
import com.example.cursach.data.network.ArticlesService
import com.example.cursach.data.persistence.ArticlesDatabase
import com.example.cursach.data.toDomain
import com.example.cursach.data.toEntity
import com.example.cursach.device.ConnectionManager
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val service: ArticlesService,
    private val db: ArticlesDatabase,
    private val connectionManager: ConnectionManager
) {

    suspend fun getArticles(): OpResult<List<Article>> {
        return try {
            when {
                connectionManager.isConnected -> Success(networkArticles())
                else -> Success(cachedArticles())
            }
        } catch (e: Exception) {
            Failure(e)
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
