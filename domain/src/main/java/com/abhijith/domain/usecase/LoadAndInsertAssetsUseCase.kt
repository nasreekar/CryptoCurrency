package com.abhijith.domain.usecase

import com.abhijith.domain.repository.ICurrencyRepository

class LoadAndInsertAssetsUseCase(private val repository: ICurrencyRepository) {
    suspend operator fun invoke() =
        repository.loadAndInsertCurrencies()
}