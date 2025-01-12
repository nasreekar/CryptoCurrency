package com.abhijith.data.di

import androidx.room.Room
import com.abhijith.data.database.CurrencyDatabase
import com.abhijith.data.repository.ICurrencyRepositoryImpl
import com.abhijith.data.utils.AndroidAssetLoader
import com.abhijith.data.utils.AssetLoader
import com.abhijith.domain.repository.ICurrencyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            CurrencyDatabase::class.java,
            "currency_database"
        ).build()
    }

    single<CoroutineDispatcher> { Dispatchers.IO }

    single { get<CurrencyDatabase>().currencyDao() }

    single<AssetLoader> { AndroidAssetLoader(androidContext()) }

    single<ICurrencyRepository> { ICurrencyRepositoryImpl(get(), get(), get()) }
}