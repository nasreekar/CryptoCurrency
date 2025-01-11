package com.abhijith.cryptocurrency.di

import com.abhijith.cryptocurrency.ui.screens.DemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { DemoViewModel(get(), get(), get(), get(), get()) }
}