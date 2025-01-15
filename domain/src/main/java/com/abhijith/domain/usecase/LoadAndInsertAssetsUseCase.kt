package com.abhijith.domain.usecase

import com.abhijith.domain.repository.CurrencyRepository

class LoadAndInsertAssetsUseCase(private val repository: CurrencyRepository) {
    suspend operator fun invoke() =
        repository.loadAndInsertCurrencies()
}