package com.example.cursach.data.repository

import com.example.cursach.Article

class NewsRepository {

    fun getArticles() = listOf( // TODO real network call
        Article(
            title = "Tas trial for alleged Christmas Day murder",
            subtitle = "bla bla bla description something bla bla bla",
            imageUrl = "https://images.unsplash.com/photo-1611267254323-4db7b39c732c?ixlib=rb-1.2.1&w=1080&fit=max&q=80&fm=jpg&crop=entropy&cs=tinysrgb",
            articleUrl = "https://www.dailymail.co.uk/news/article-11328163/Sorry-Elon-Donald-Ye-Americans-WANT-guard-rails-social-media-platforms.html",
            author = "Українська Правда"
        )
    )
}