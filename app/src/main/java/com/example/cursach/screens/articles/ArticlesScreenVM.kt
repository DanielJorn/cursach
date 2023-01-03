package com.example.cursach.screens.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursach.Article
import com.example.cursach.Failure
import com.example.cursach.Success
import com.example.cursach.data.repository.NewsRepository
import com.example.cursach.device.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesScreenVM @Inject constructor(
    private val repo: NewsRepository
) : ViewModel() {

    private val _state: MutableStateFlow<ArticlesState> = MutableStateFlow(
        ArticlesState(listOf(), null,true, Language.Ukrainian)
    )
    val state = _state.asStateFlow()

    init {
        loadArticles()
    }

    private fun loadArticles() {
        viewModelScope.launch {
            when (val result = repo.getArticles()) {
                is Success -> _state.value = ArticlesState(result.data, null, false, Language.Ukrainian)
                is Failure -> _state.value = _state.value.copy(error = result.reason)
            }
        }
    }

    fun translateArticle(article: Article) {
        viewModelScope.launch {
            when (val result = repo.translateArticle(article, state.value.chosenLanguage)) {
                is Success -> {
                    val newList = _state.value.articles.toMutableList().apply {
                        val indexOf = indexOf(article)
                        if (indexOf == -1) return@launch

                        removeAt(indexOf)
                        add(indexOf, result.data)
                    }
                    _state.value = _state.value.copy(articles = newList)
                }
                is Failure -> _state.value = _state.value.copy(error = result.reason)
            }
        }
    }

    fun articleClicked(article: Article) {
        _state.value = _state.value.copy(currentViewedArticle = article)
    }

    fun articleClosed() {
        _state.value = _state.value.copy(currentViewedArticle = null)
    }

    fun changeLanguage(language: Language) {
        _state.value = state.value.copy(chosenLanguage = language)
    }
}

data class ArticlesState(
    val articles: List<Article>,
    val error: Exception?,
    val loading: Boolean,
    val chosenLanguage: Language,
    val currentViewedArticle: Article? = null
)
