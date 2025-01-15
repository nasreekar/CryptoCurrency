package com.abhijith.cryptocurrency.ui.screens.currencyList.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.abhijith.domain.model.Currency


@Composable
fun CurrencyData(currencies: List<Currency>) {
    LazyColumn(
        modifier = Modifier
            .testTag("currency_data")
            .fillMaxWidth()
    ) {
        items(currencies.size) { index ->
            CurrencyListItem(currency = currencies[index])
        }
    }
}
