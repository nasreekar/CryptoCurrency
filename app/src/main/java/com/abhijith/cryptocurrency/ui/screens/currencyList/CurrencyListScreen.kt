package com.abhijith.cryptocurrency.ui.screens.currencyList

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhijith.cryptocurrency.ui.components.LoadingScreen
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyListScreen(
    type: CurrencyType, viewModel: CurrencyListViewModel = koinViewModel()
) {
    val currencies by viewModel.currencies.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(type) {
        viewModel.loadCurrencies(type)
    }

    when (uiState) {
        is CurrencyListState.Loading -> {
            LoadingScreen()
        }

        is CurrencyListState.Error -> {
            val errorMessage = (uiState as CurrencyListState.Error).message
            Log.e("CurrencyListScreen", errorMessage)
        }

        else -> {
            CurrencyListContent(currencies = currencies)
        }
    }
}
