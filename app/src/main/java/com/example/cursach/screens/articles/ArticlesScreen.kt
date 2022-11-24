package com.example.cursach.screens.articles

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Translate
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cursach.Article
import kotlinx.coroutines.launch

@Composable
fun ArticlesScreen(
    vm: ArticlesScreenVM = viewModel(),
    scaffoldState: ScaffoldState,
    padding: PaddingValues
) {
    val state = vm.state.collectAsState()

    NewsFeed(
        state = state.value,
        snackbarHostState = scaffoldState.snackbarHostState,
        padding = padding,
        onTranslateClicked = { vm.translateArticle(it) },
        onCardClick = { vm.articleClicked(it) },
        onCloseWebView = { vm.articleClosed() },
    )
}

@Composable
fun NewsFeed(
    state: ArticlesState,
    snackbarHostState: SnackbarHostState,
    padding: PaddingValues,
    onTranslateClicked: (Article) -> Unit,
    onCardClick: (Article) -> Unit,
    onCloseWebView: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    LaunchedEffect(state.error) {
        if (state.error != null) {
            scope.launch { snackbarHostState.showSnackbar("oh-oh everything brokken") }
        }
    }

    Crossfade(targetState = state.currentViewedArticle, animationSpec = tween(900)) { article ->
        if (article != null) {
            BackHandler {
                onCloseWebView()
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AndroidView(factory = {
                    WebView(it).apply {
                        webViewClient = WebViewClient()

                        loadUrl(article.articleUrl)
                        settings.javaScriptEnabled = true
                    }
                })
            }
        } else {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(MaterialTheme.colors.background)
            ) {
                Crossfade(targetState = state.loading, animationSpec = tween(900)) { loading ->
                    when {
                        loading -> LazyColumn(userScrollEnabled = false) {
                            items(10) {
                                Dummy()
                            }
                        }
                        else -> LazyColumn(state = scrollState) {
                            items(items = state.articles) {
                                Card(
                                    Modifier
                                        .padding(6.dp)
                                        .clickable { onCardClick(it) }) {
                                    Column(Modifier.padding(16.dp)) {
                                        Row {
                                            Column(Modifier.weight(0.8f)) {
                                                Text(
                                                    text = it.author,
                                                    style = MaterialTheme.typography.caption,
                                                    color = Color.Gray
                                                )
                                                Text(
                                                    text = it.title,
                                                    style = MaterialTheme.typography.h6,
                                                )
                                                Spacer(Modifier.size(4.dp))
                                                Text(
                                                    it.subtitle,
                                                    style = MaterialTheme.typography.body2,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }
                                            if (it.imageUrl.isNotBlank()) {
                                                Spacer(Modifier.width(16.dp))
                                                AsyncImage(
                                                    model = it.imageUrl,
                                                    modifier = Modifier
                                                        .size(60.dp)
                                                        .clip(MaterialTheme.shapes.medium)
                                                        .background(Black),
                                                    contentScale = ContentScale.Crop,
                                                    contentDescription = "",
                                                )
                                            }
                                        }
                                        Spacer(Modifier.height(if (it.imageUrl.isNotBlank()) 8.dp else 16.dp))
                                        Box(Modifier.fillMaxWidth()) {
                                            Text(
                                                modifier = Modifier.align(Alignment.BottomStart),
                                                text = "1d",
                                                style = MaterialTheme.typography.caption,
                                            )
                                            IconButton(
                                                modifier = Modifier
                                                    .align(Alignment.CenterEnd)
                                                    .size(20.dp),
                                                onClick = { onTranslateClicked(it) }
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Translate,
                                                    contentDescription = "",
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun Dummy() {
    val transition = rememberInfiniteTransition()
    val rightGradientBorder by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                delayMillis = remember { listOf(100, 200, 300).random() },
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val widthFraction = remember { (50..80).random() / 100f }
    val height = remember { listOf(50, 75, 100, 125).random() }
    Card(
        modifier = Modifier
            .alpha(0.7f)
            .padding(6.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth(widthFraction)
                .height(height.dp)
                .drawBehind {
                    drawRect(
                        brush = Brush.horizontalGradient(
                            0f to Color.Gray,
                            rightGradientBorder to Color.White,
                            1f to Color.Gray,
                        ),
                        alpha = 0.5f
                    )
                }
        ) {

        }
    }
}

