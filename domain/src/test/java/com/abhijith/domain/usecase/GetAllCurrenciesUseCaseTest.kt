package com.abhijith.domain.usecase

import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.CurrencyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllCurrenciesUseCaseTest {

    private val repository: CurrencyRepository = mockk()
    private val getAllCurrenciesUseCase = GetAllCurrenciesUseCase(repository)

    @Test
    fun `verify invoke should return list of currencies from repository`() = runTest {
        val currencies = listOf(
            Currency(
                id = "BTC",
                name = "Bitcoin",
                symbol = "BTC"
            ), Currency(
                id = "ETH",
                name = "Ethereum Classic",
                symbol = "ETH"
            ), Currency(
                id = "SGD",
                name = "Singapore Dollar",
                symbol = "$",
                code = "SGD"
            )
        )
        coEvery { repository.getAllCurrencies() } returns flowOf(currencies)

        val result = getAllCurrenciesUseCase.invoke().first()

        assertEquals(currencies, result)
        coVerify(exactly = 1) { repository.getAllCurrencies() }
    }
}