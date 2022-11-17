package com.example.cursach.screens.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursach.*
import com.example.cursach.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesScreenVM @Inject constructor(
    private val repo: NewsRepository
) : ViewModel() {

    private val _state: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(listOf(), null, true))
    val state = _state.asStateFlow()

    init {
        loadArticles()
    }

    private fun loadArticles() {
        viewModelScope.launch {
            when (val result = repo.getArticles()) {
                is Success -> _state.value = ArticlesState(result.data, null, false)
                is Failure -> _state.value = _state.value.copy(error = result.reason)
            }
        }
    }
}

data class ArticlesState(
    val articles: List<Article>,
    val error: Exception?,
    val loading: Boolean
)