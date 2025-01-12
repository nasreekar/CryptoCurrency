package com.abhijith.cryptocurrency.ui.screens.currencyList

sealed class CurrencyListState {
    data object Loading : CurrencyListState()
    data object Success : CurrencyListState()
    data class Error(val message: String) : CurrencyListState()
}
