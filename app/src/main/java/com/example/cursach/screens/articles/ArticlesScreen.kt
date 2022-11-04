package com.example.cursach.screens.articles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cursach.Article
import com.example.cursach.R
import com.example.cursach.data.repository.NewsRepository

@Composable
fun ArticlesScreen(
    vm: ArticlesScreenVM = ArticlesScreenVM(NewsRepository()) // todo DI
) {
    val state = vm.articles.collectAsState()

    NewsFeed(state.value)
}

@Composable
fun NewsFeed(articles: List<Article>) {
    LazyColumn {
        items(articles) {
            Card(Modifier.padding(6.dp)) {
                Row(Modifier.padding(8.dp)) {
                    Column(Modifier.fillMaxWidth(0.86f)) {
                        Text(it.author, style = MaterialTheme.typography.caption, color = Color.Gray)
                        Text(it.title, style = MaterialTheme.typography.h5)
                        Spacer(Modifier.size(4.dp))
                        Text(it.subtitle, style = MaterialTheme.typography.subtitle1)
                    }
                    Image(
                        modifier = Modifier.size(50.dp).clip(MaterialTheme.shapes.small),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "",
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNewsFeed() {
    NewsFeed(
        List(10)  {
            Article(
                "назва",
                "опис",
                "назва",
                "назва",
                "автор",
            )
        }
    )
}