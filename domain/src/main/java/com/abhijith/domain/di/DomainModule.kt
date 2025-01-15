package com.abhijith.domain.di

import com.abhijith.domain.interactor.CurrencyUseCaseInteractor
import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUseCase
import com.abhijith.domain.usecase.GetFiatCurrencyUseCase
import com.abhijith.domain.usecase.LoadAndInsertAssetsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetAllCurrenciesUseCase(get()) }
    factory { LoadAndInsertAssetsUseCase(get()) }
    factory { ClearCurrenciesUseCase(get()) }
    factory { GetCryptoCurrencyUseCase(get()) }
    factory { GetFiatCurrencyUseCase(get()) }
    single { CurrencyUseCaseInteractor(get(), get(), get(), get(), get()) }
}