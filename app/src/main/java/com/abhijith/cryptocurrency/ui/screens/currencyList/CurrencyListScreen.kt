package com.abhijith.cryptocurrency.ui.screens.currencyList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.common.ErrorScreen
import com.abhijith.cryptocurrency.ui.common.LoadingScreen
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.cryptocurrency.ui.screens.currencyList.components.CurrencyListContent
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

    Box(
        modifier = Modifier
            .testTag("currency_list_screen_container_${type.value}")
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (uiState) {
            is CurrencyListState.Loading -> {
                LoadingScreen()
            }

            is CurrencyListState.Empty -> {
                ErrorScreen(
                    android.R.drawable.stat_notify_error,
                    stringResource(id = R.string.no_records_in_db)
                )
            }

            is CurrencyListState.Error -> {
                ErrorScreen(
                    android.R.drawable.stat_notify_error,
                    stringResource(
                        (uiState as CurrencyListState.Error).messageResId,
                        *((uiState as CurrencyListState.Error).formatArgs.toTypedArray())
                    )
                )
            }

            is CurrencyListState.Success -> {
                CurrencyListContent(currencies = currencies)
            }

            else -> {
                // Handle other states if necessary
            }
        }
    }
}