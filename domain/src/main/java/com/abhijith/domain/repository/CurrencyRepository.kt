package com.abhijith.domain.repository

import com.abhijith.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getAllCurrencies(): Flow<List<Currency>>
    fun getCryptoCurrencies(): Flow<List<Currency>>
    fun getFiatCurrencies(): Flow<List<Currency>>
    suspend fun clearCurrencies()
    suspend fun loadAndInsertCurrencies()
}