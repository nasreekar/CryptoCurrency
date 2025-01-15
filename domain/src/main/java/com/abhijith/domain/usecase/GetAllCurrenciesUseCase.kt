package com.abhijith.domain.usecase

import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetAllCurrenciesUseCase(private val repository: CurrencyRepository) {
    operator fun invoke(): Flow<List<Currency>> {
        return repository.getAllCurrencies()
    }
}