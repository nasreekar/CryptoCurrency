package com.abhijith.cryptocurrency.ui.navigation

sealed class Screen(val route: String) {
    data object Demo : Screen("demo")
    data object CurrencyList : Screen("currency_list")
}