package com.abhijith.domain.usecase

import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetFiatCurrenciesUseCaseTest {

    private val repository: ICurrencyRepository = mockk()
    private val getFiatCurrencyUseCase = GetFiatCurrencyUseCase(repository)

    @Test
    fun `verify invoke should return list of currencies from repository`() = runBlocking {
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
        coEvery { repository.getFiatCurrencies() } returns flowOf(currencies)

        val result = getFiatCurrencyUseCase.invoke().first()

        assertEquals(currencies, result)
        coVerify(exactly = 1) { repository.getFiatCurrencies() }
    }
}