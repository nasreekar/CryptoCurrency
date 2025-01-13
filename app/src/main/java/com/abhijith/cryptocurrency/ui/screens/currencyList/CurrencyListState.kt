package com.abhijith.cryptocurrency.ui.screens.currencyList

sealed class CurrencyListState {
    data object Loading : CurrencyListState()
    data object Success : CurrencyListState()
    data class Error(
        val messageResId: Int,
        val formatArgs: List<String> = emptyList()
    ) : CurrencyListState()
    data object Empty : CurrencyListState()
}
