package com.abhijith.cryptocurrency.ui.screens.currencyList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abhijith.domain.model.Currency

@Composable
fun CurrencyListScreen(currencies: List<Currency>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Currency List")
        Text("Dummy text")
        Text("Dummy text")
        Text("Dummy text")
    }
}