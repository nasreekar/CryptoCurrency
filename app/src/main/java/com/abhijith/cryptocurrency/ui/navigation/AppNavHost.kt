package com.abhijith.cryptocurrency.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.cryptocurrency.ui.screens.currencyList.CurrencyListScreen
import com.abhijith.cryptocurrency.ui.screens.demo.DemoScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Demo.route) {
        composable(Screen.Demo.route) {
            DemoScreen(
                onNavigateToCurrencyList = { type ->
                    navController.navigate("${Screen.CurrencyList.route}/$type")
                }
            )
        }

        composable(
            route = "${Screen.CurrencyList.route}/{type}",
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type").orEmpty()
            val currencyType = CurrencyType.fromValue(type)
            CurrencyListScreen(type = currencyType)
        }
    }
}