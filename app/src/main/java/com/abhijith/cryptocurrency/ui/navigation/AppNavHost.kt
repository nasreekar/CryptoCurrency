package com.abhijith.cryptocurrency.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abhijith.cryptocurrency.ui.screens.currencyList.CurrencyListScreen
import com.abhijith.cryptocurrency.ui.screens.demo.DemoScreen
import com.abhijith.domain.model.Currency
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Demo.route) {
        composable(Screen.Demo.route) {
            DemoScreen(
                onNavigateToCurrencyList = { currencies ->
                    val currenciesJson = Json.encodeToString(currencies)
                    navController.navigate("${Screen.CurrencyList.route}/$currenciesJson")
                }
            )
        }

        composable(
            route = "${Screen.CurrencyList.route}/{currencies}",
            arguments = listOf(navArgument("currencies") { type = NavType.StringType })
        ) { backStackEntry ->
            val currenciesJson = backStackEntry.arguments?.getString("currencies")
            val currencies = remember(currenciesJson) {
                Json.decodeFromString<List<Currency>>(currenciesJson ?: "[]")
            }
            CurrencyListScreen(currencies = currencies)
        }
    }
}