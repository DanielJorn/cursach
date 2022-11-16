package com.example.cursach.data

import com.example.cursach.Article
import com.example.cursach.data.network.model.ArticleResponse
import com.example.cursach.data.persistence.model.ArticleEntity

fun ArticleEntity.toDomain() = Article(
    title = title,
    subtitle = subtitle,
    imageUrl = imageUrl,
    articleUrl = articleUrl,
    author = author
)

fun ArticleResponse.toDomain() = Article(
    title = title,
    subtitle = description ?: content,
    imageUrl = urlToImage,
    articleUrl = url,
    author = source.name
)

fun ArticleResponse.toEntity() = ArticleEntity(
    title = title,
    subtitle = description ?: content,
    imageUrl = urlToImage,
    articleUrl = url,
    author = source.name
)