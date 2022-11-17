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
            ),
            ArticleEntity(
                "назва збереженого 12312421421423421321",
                "контент збереженого",
                "",
                "",
                "автор",
            ),
            ArticleEntity(
                "назва збереженого збереженого",
                "контент збереженого",
                "",
                "",
                "автор",
            ),
            ArticleEntity(
                "назва збереженого 1232",
                "контент збереженого",
                "",
                "",
                "автор",
            ),

        )
    }

    suspend fun saveArticles(entities: List<ArticleEntity>) {

    }
}
