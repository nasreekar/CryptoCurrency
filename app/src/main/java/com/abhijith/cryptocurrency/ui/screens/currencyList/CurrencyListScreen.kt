package com.abhijith.cryptocurrency.ui.screens.currencyList

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.components.LoadingScreen
import com.abhijith.cryptocurrency.ui.components.NoResultsFound
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyListScreen(
    type: CurrencyType, viewModel: CurrencyListViewModel = koinViewModel()
) {
    val currencies by viewModel.currencies.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var snackbarMessage by remember { mutableStateOf("") }

    snackbarMessage = when (uiState) {
        is CurrencyListState.Error -> stringResource(
            (uiState as CurrencyListState.Error).messageResId,
            *((uiState as CurrencyListState.Error).formatArgs.toTypedArray())
        )

        else -> ""
    }

    LaunchedEffect(type) {
        viewModel.loadCurrencies(type)
    }

    when (uiState) {
        is CurrencyListState.Loading -> {
            LoadingScreen()
        }

        is CurrencyListState.Empty -> {
            NoResultsFound(
                android.R.drawable.stat_notify_error,
                stringResource(id = R.string.no_records_in_db)
            )
        }

        is CurrencyListState.Error -> {
            Log.d("Abhijith - CurrencyListScreen", snackbarMessage)
        }

        is CurrencyListState.Success -> {
            CurrencyListContent(currencies = currencies)
        }

        else -> {
            // Handle other states if necessary
        }
    }
}