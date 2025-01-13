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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.components.CryptoSnackBar
import com.abhijith.cryptocurrency.ui.screens.demo.components.DemoScreenActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun DemoScreen(
    viewModel: DemoViewModel = koinViewModel(),
    onNavigateToCurrencyList: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    snackbarMessage = when (uiState) {
        is DemoUiState.Success -> stringResource((uiState as DemoUiState.Success).messageResId)
        is DemoUiState.Error -> stringResource(
            (uiState as DemoUiState.Error).messageResId,
            *((uiState as DemoUiState.Error).formatArgs.toTypedArray())
        )

        is DemoUiState.Loading -> stringResource(R.string.loading)
        else -> ""
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            is DemoUiState.NavigateToCurrencyList -> {
                onNavigateToCurrencyList((uiState as DemoUiState.NavigateToCurrencyList).type)
                viewModel.resetUiState()
            }

            is DemoUiState.Success, is DemoUiState.Error, is DemoUiState.Loading -> {
                showSnackbar = true
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
    }
}