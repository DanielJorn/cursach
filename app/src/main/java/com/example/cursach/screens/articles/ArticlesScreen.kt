package com.example.cursach.screens.articles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        LazyColumn {
            items(state.articles) {
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
