package com.example.cursach

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.ReturnCode
import com.example.cursach.ui.theme.CursachTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @Composable
    fun NewsItem(
        news: News,
        onClick: (News) -> Unit
    ) {
        Card(
            backgroundColor = Color.Red
        ) {
            Text("a")
        }
    }

    data class News(
        val title: String,
        val subtitle: String,
        val imageUrl: String,
        val articleUrl: String,
        val author: String
    )


    @Composable
    fun NewsFeed(news: List<News>) {
        LazyColumn {
            items(news) {
                NewsItem(it) { }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewNewsItem() {
        NewsItem(
            News(
                title = "titie",
                subtitle = "subtitle",
                imageUrl = "https://images.unsplash.com/photo-1611267254323-4db7b39c732c?ixlib=rb-1.2.1&w=1080&fit=max&q=80&fm=jpg&crop=entropy&cs=tinysrgb",
                articleUrl = "https://www.dailymail.co.uk/news/article-11328163/Sorry-Elon-Donald-Ye-Americans-WANT-guard-rails-social-media-platforms.html",
                author = "Українська Правда"
            )
        ) {

        }
    }

    @Preview
    @Composable
    fun PreviewNewsFeed() {
        CursachTheme {
            //A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                NewsFeed(
                    List(10) {
                        News(
                            title = "titie",
                            subtitle = "subtitle",
                            imageUrl = "https://images.unsplash.com/photo-1611267254323-4db7b39c732c?ixlib=rb-1.2.1&w=1080&fit=max&q=80&fm=jpg&crop=entropy&cs=tinysrgb",
                            articleUrl = "https://www.dailymail.co.uk/news/article-11328163/Sorry-Elon-Donald-Ye-Americans-WANT-guard-rails-social-media-platforms.html",
                            author = "Українська Правда"
                        )
                    }
                )
            }
        }
    }
}
