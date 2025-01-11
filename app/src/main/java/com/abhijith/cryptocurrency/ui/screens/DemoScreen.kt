package com.abhijith.cryptocurrency.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun DemoScreen(viewModel: DemoViewModel = koinViewModel(), modifier: Modifier = Modifier) {
    val data by viewModel.dummyData.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        Text(text = data)
    }
}