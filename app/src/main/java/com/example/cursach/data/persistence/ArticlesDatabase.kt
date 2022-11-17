package com.example.cursach.data.persistence

import com.example.cursach.data.persistence.model.ArticleEntity
import javax.inject.Inject

class ArticlesDatabase @Inject constructor() {
    suspend fun getArticles(): List<ArticleEntity> {
        return listOf(
            ArticleEntity(
                "назва збереженого",
                "контент збереженого",
                "",
                "",
                "автор",
            )
        )
    }

    suspend fun saveArticles(entities: List<ArticleEntity>) {

    }
}
