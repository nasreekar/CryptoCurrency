package com.abhijith.domain.usecase

import com.abhijith.domain.repository.ICurrencyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ClearCurrenciesUseCaseTest {

    private val repository: ICurrencyRepository = mockk()
    private val clearCurrenciesUseCase = ClearCurrenciesUseCase(repository)

    @Test
    fun `invoke should call clearCurrencies on repository`() = runBlocking {
        coEvery { repository.clearCurrencies() } returns Unit
        clearCurrenciesUseCase.invoke()
        coVerify(exactly = 1) { repository.clearCurrencies() }
    }
}