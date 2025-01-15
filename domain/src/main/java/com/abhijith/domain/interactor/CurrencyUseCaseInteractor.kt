package com.abhijith.domain.interactor

import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUseCase
import com.abhijith.domain.usecase.GetFiatCurrencyUseCase
import com.abhijith.domain.usecase.LoadAndInsertAssetsUseCase

class CurrencyUseCaseInteractor(
    private val getCryptoCurrencyUseCase: GetCryptoCurrencyUseCase,
    private val getFiatCurrencyUseCase: GetFiatCurrencyUseCase,
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val clearCurrenciesUseCase: ClearCurrenciesUseCase,
    private val loadAndInsertAssetsUseCase: LoadAndInsertAssetsUseCase
) {
    fun getCryptoCurrencies() = getCryptoCurrencyUseCase.invoke()
    fun getFiatCurrencies() = getFiatCurrencyUseCase.invoke()
    fun getAllCurrencies() = getAllCurrenciesUseCase.invoke()
    suspend fun clearCurrencies() = clearCurrenciesUseCase.invoke()
    suspend fun loadAndInsertAssets() = loadAndInsertAssetsUseCase.invoke()
}