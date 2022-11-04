package com.example.cursach.screens.articles

import androidx.lifecycle.ViewModel
import com.example.cursach.Article
import com.example.cursach.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArticlesScreenVM(
    private val repo: NewsRepository // TODO DI
): ViewModel() {

    private val _articles: MutableStateFlow<List<Article>> = MutableStateFlow(listOf())
    val articles = _articles.asStateFlow()

    init {
        loadArticles()
    }

    private fun loadArticles() {
        _articles.value = repo.getArticles()
    }
}