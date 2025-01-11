package com.abhijith.domain.usecase

import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository

class InsertCurrenciesUseCase(private val repository: ICurrencyRepository) {
    suspend operator fun invoke(currencies: List<Currency>) =
        repository.insertCurrencies(currencies)
}