package com.example.cursach.screens.articles

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursach.R
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
    )
}

@Composable
fun NewsFeed(
    state: ArticlesState,
    snackbarHostState: SnackbarHostState,
    padding: PaddingValues
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.error) {
        if (state.error != null) {
            scope.launch { snackbarHostState.showSnackbar("oh-oh everything brokken") }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(padding)
            .background(MaterialTheme.colors.background)
    ) {
        Crossfade(targetState = state, animationSpec = tween(900)) {
            when {
                it.articles.isEmpty() -> LazyColumn(userScrollEnabled = false) {
                    items(10) {
                        Dummy()
                    }
                }
                else -> LazyColumn {
                    items(it.articles) {
                        Card(Modifier.padding(6.dp)) {
                            Row(
                                Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Column {
                                    Text(
                                        text = it.author,
                                        style = MaterialTheme.typography.caption,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = it.title,
                                        style = MaterialTheme.typography.h5,
                                    )
                                    Spacer(Modifier.size(4.dp))
                                    Text(it.subtitle, style = MaterialTheme.typography.subtitle1)
                                }
                                Box(Modifier.fillMaxSize()) {
                                    Image(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clip(MaterialTheme.shapes.medium)
                                            .align(CenterEnd),
                                        painter = painterResource(id = R.drawable.ic_launcher_background),
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


@Composable
private fun Dummy() {
    val transition = rememberInfiniteTransition()
    val rightGradientBorder by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                delayMillis = listOf(100, 200, 300).random(),
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val a = (50..80).random() / 100f
    Card(
        modifier = Modifier
            .alpha(0.7f)
            .padding(6.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth(a)
                .height(100.dp)
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

