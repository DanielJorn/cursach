package com.example.cursach.data.repository

import android.util.Log
import com.example.cursach.Article
import com.example.cursach.Failure
import com.example.cursach.OpResult
import com.example.cursach.Success
import com.example.cursach.data.translation
import com.example.cursach.data.network.ArticlesService
import com.example.cursach.data.persistence.ArticlesDatabase
import com.example.cursach.data.toDomain
import com.example.cursach.data.toEntity
import com.example.cursach.device.ConnectionManager
import com.example.cursach.device.Language
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
            Log.e("kekw", "getArticles() failed", e)
            e.printStackTrace()
            Failure(e)
        }
    }

    private suspend fun cachedArticles() = db.getArticles().map { it.toDomain() }

    private suspend fun networkArticles(): List<Article> {
        val response = service.getArticles()

        val entities = response.data.map { it.toEntity() }
        db.saveArticles(entities)

        return response.data.map { it.toDomain() }
    }

    suspend fun translateArticle(article: Article, language: Language): OpResult<Article> {
        return try {
            val responseTitle = service.translate(article.title, language.code)
            val responseContent = service.translate(article.subtitle, language.code)

            Success(article.copy(title = responseTitle.translation!!, subtitle = responseContent.translation!!))
        } catch (e: Exception) {
            Log.e("kekw", "translateArticle() failed", e)
            Failure(e)
        }
    }
}
