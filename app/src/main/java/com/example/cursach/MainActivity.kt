package com.example.cursach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.cursach.screens.articles.ArticlesScreen
import com.example.cursach.ui.theme.CursachTheme

class MainActivity1 : ComponentActivity() {
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
        Scaffold { innerPadding ->
            ArticlesScreen()
        }
    }
}
