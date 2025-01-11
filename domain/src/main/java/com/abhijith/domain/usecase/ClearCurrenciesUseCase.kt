package com.abhijith.domain.usecase

import com.abhijith.domain.repository.ICurrencyRepository

class ClearCurrenciesUseCase(private val repository: ICurrencyRepository) {
    suspend operator fun invoke() = repository.clearCurrencies()
}