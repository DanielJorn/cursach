package com.example.cursach.data.persistence

import com.example.cursach.data.persistence.model.ArticleEntity

class ArticlesDatabase {
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
