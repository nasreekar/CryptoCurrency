package com.abhijith.cryptocurrency.ui.screens.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun DemoScreen(viewModel: DemoViewModel = koinViewModel()) {
    val data by viewModel.dummyData.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {

        DemoScreenActions(
            onClearDatabase = { // viewModel.clearCurrencies()
            },
            onInsertData = { // viewModel.insertData()
            },
            onShowCrypto = { // viewModel.showCryptoCurrencies()
            },
            onShowFiat = { // viewModel.showFiatCurrencies()
            },
            onShowAll = { // viewModel.showAllCurrencies()
            }
        )
    }
}

@Composable
@Preview
fun DemoScreenPreview() {
    DemoScreen()
}