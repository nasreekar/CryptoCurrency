package com.abhijith.cryptocurrency.ui.screens.currencyList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.components.NoResultsFound
import com.abhijith.cryptocurrency.ui.screens.currencyList.components.CurrencyListItem
import com.abhijith.cryptocurrency.ui.screens.currencyList.components.CurrencySearchBar
import com.abhijith.cryptocurrency.ui.utils.matchesSearchCriteria
import com.abhijith.domain.model.Currency

@Composable
fun CurrencyListContent(currencies: List<Currency>) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val filteredCurrencies = currencies.matchesSearchCriteria(searchQuery.text)

    Column(modifier = Modifier.fillMaxSize()) {
        CurrencySearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            onClearSearch = { searchQuery = TextFieldValue("") }
        )
        if (filteredCurrencies.isEmpty()) {
            NoResultsFound(
                imgRes = android.R.drawable.ic_menu_search,
                message = stringResource(id = R.string.no_results_found)
            )
        } else {
            CurrencyData(currencies = filteredCurrencies)
        }
    }
}

@Composable
fun CurrencyData(currencies: List<Currency>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(currencies.size) { index ->
            CurrencyListItem(currency = currencies[index])
        }
    }
}
