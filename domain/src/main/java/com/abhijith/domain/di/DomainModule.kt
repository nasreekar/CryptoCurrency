package com.abhijith.domain.di

import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUserCase
import com.abhijith.domain.usecase.GetFiatCurrencyUserCase
import com.abhijith.domain.usecase.InsertCurrenciesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetAllCurrenciesUseCase(get()) }
    factory { InsertCurrenciesUseCase(get()) }
    factory { ClearCurrenciesUseCase(get()) }
    factory { GetCryptoCurrencyUserCase(get()) }
    factory { GetFiatCurrencyUserCase(get()) }
}