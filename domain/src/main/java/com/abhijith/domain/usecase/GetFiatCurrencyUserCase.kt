package com.abhijith.domain.usecase

import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow

class GetFiatCurrencyUserCase(private val repository: ICurrencyRepository) {
    operator fun invoke(): Flow<List<Currency>> {
        return repository.getFiatCurrencies()
    }
}