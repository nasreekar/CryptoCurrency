package com.abhijith.cryptocurrency.di

import com.abhijith.cryptocurrency.ui.screens.currencyList.CurrencyListViewModel
import com.abhijith.cryptocurrency.ui.screens.demo.DemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { DemoViewModel(get(), get()) }
    viewModel { CurrencyListViewModel(get(), get(), get()) }
}