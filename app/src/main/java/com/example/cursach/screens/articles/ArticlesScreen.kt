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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursach.Article
import com.example.cursach.R
import com.example.cursach.data.repository.NewsRepository

@Composable
fun ArticlesScreen(
    vm: ArticlesScreenVM = viewModel()
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
                    Column {
                        Text(
                            text = it.author,
                            style = MaterialTheme.typography.caption,
                            color = Color.Gray
                        )
                        Row {
                            Text(
                                text = it.title,
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.weight(0.8f)
                            )
                            Image(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(MaterialTheme.shapes.small)
                                    .align(CenterVertically)
                                    .weight(0.2f),
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = "",
                            )
                        }
                        Spacer(Modifier.size(4.dp))
                        Text(it.subtitle, style = MaterialTheme.typography.subtitle1)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNewsFeed() {
    NewsFeed(
        List(10) {
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