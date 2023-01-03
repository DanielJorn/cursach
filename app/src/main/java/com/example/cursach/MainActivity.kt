package com.example.cursach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cursach.screens.articles.ArticlesScreen
import com.example.cursach.ui.theme.CursachTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    CursachTheme {
        val state = rememberScaffoldState()
        Scaffold(
            scaffoldState = state,
            topBar = { AppBar() }
        ) { innerPadding ->
            ArticlesScreen(
                scaffoldState = state,
                padding = innerPadding
            )
        }
    }
}

@Composable
private fun AppBar() {
    Box(Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp),
            text = "News Elephant",
            style = MaterialTheme.typography.h5
        )
    }
}
