package com.example.cursach.screens.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursach.Article
import com.example.cursach.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesScreenVM @Inject constructor(
    private val repo: NewsRepository
): ViewModel() {

    private val _articles: MutableStateFlow<List<Article>> = MutableStateFlow(listOf())
    val articles = _articles.asStateFlow()

    init {
        loadArticles()
    }

    private fun loadArticles() {
        viewModelScope.launch {
            _articles.value = repo.getArticles()
        }
    }
}