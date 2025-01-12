package com.abhijith.cryptocurrency.ui.screens.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhijith.cryptocurrency.ui.components.CryptoSnackBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun DemoScreen(
    viewModel: DemoViewModel = koinViewModel(),
    onNavigateToCurrencyList: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        when (uiState) {
            is DemoUiState.NavigateToCurrencyList -> {
                onNavigateToCurrencyList((uiState as DemoUiState.NavigateToCurrencyList).type)
                viewModel.resetUiState()
            }

            is DemoUiState.Success -> {
                showSnackbar = true
                snackbarMessage = (uiState as DemoUiState.Success).message
            }

            is DemoUiState.Error -> {
                showSnackbar = true
                snackbarMessage = (uiState as DemoUiState.Error).message
            }

            is DemoUiState.Loading -> {
                showSnackbar = true
                snackbarMessage = "Loading..."
            }

            else -> {}
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {

        DemoScreenActions(
            onClearDatabase = {
                viewModel.clearCurrencies()
            },
            onInsertData = {
                viewModel.insertData()
            },
            onShowCrypto = {
                viewModel.showCryptoCurrencies()
            },
            onShowFiat = {
                viewModel.showFiatCurrencies()
            },
            onShowAll = {
                viewModel.showAllCurrencies()
            }
        )
    }


    if (showSnackbar) {
        CryptoSnackBar(message = snackbarMessage, onDismiss = { showSnackbar = false })
//        LaunchedEffect(Unit) {
//            kotlinx.coroutines.delay(5000)
//            showSnackbar = false
//        }
    }
}