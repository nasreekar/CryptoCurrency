package com.abhijith.cryptocurrency.di

import com.abhijith.cryptocurrency.ui.screens.demo.DemoViewModel
import com.abhijith.cryptocurrency.ui.utils.AndroidAssetLoader
import com.abhijith.cryptocurrency.ui.utils.AssetLoader
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AssetLoader> { AndroidAssetLoader(androidContext()) }

    viewModel { DemoViewModel(get(), get(), get()) }
}