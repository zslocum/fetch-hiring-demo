package com.cryptictruth.fetchhiringdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.cryptictruth.fetchhiringdemo.screens.HiringListScreen
import com.cryptictruth.fetchhiringdemo.ui.theme.FetchHiringDemoTheme
import com.cryptictruth.fetchhiringdemo.viewmodels.HiringViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: HiringViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchHiringDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HiringListScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                    )
                }
            }
        }
    }
}