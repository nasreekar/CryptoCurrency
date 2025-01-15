package com.abhijith.domain.usecase

import com.abhijith.domain.repository.CurrencyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ClearCurrenciesUseCaseTest {

    private val repository: CurrencyRepository = mockk()
    private val clearCurrenciesUseCase = ClearCurrenciesUseCase(repository)

    @Test
    fun `verify invoke should call clearCurrencies on repository`() = runTest {
        coEvery { repository.clearCurrencies() } returns Unit
        clearCurrenciesUseCase.invoke()
        coVerify(exactly = 1) { repository.clearCurrencies() }
    }
}