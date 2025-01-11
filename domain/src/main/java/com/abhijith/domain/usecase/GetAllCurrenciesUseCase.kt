package com.abhijith.domain.usecase

import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetAllCurrenciesUseCase(private val repository: ICurrencyRepository) {
    suspend operator fun invoke(): Flow<List<Currency>> {
        return repository.getAllCurrencies()
    }
}