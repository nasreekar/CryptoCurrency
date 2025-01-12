package com.abhijith.cryptocurrency.ui.screens.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhijith.domain.model.Currency
import org.koin.androidx.compose.koinViewModel

@Composable
fun DemoScreen(
    viewModel: DemoViewModel = koinViewModel(),
    onNavigateToCurrencyList: (List<Currency>) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        when (uiState) {
            is DemoUiState.NavigateToCurrencyList -> {
                onNavigateToCurrencyList((uiState as DemoUiState.NavigateToCurrencyList).currencies)
            }

            is DemoUiState.Success -> {
                showSnackbar = true
                snackbarMessage = (uiState as DemoUiState.Success).message
            }

            is DemoUiState.Error -> {
                showSnackbar = true
                snackbarMessage = (uiState as DemoUiState.Error).message
            }

            else -> {}
        }
    }


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


    if (showSnackbar) {
        Snackbar(
            // onDismissed = { showSnackbar = false }
        ) {
            Text(snackbarMessage)
        }
    }
}