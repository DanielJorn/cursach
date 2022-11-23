package com.example.cursach.data

import com.example.cursach.Article
import com.example.cursach.data.network.model.ArticlesDataResponse
import com.example.cursach.data.persistence.model.ArticleEntity

fun ArticleEntity.toDomain() = Article(
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl,
    articleUrl = articleUrl,
    author = author
)

fun ArticlesDataResponse.toDomain() = Article(
    title = title ?: "",
    subtitle = description ?: "",
    imageUrl = image ?: "",
    articleUrl = url ?: "",
    author = author ?: source ?: ""
)

fun ArticlesDataResponse.toEntity() = ArticleEntity(
    title = title ?: "",
    subtitle = description ?: "",
    imageUrl = image ?: "",
    articleUrl = url ?: "",
    author = author ?: source ?: ""
)
