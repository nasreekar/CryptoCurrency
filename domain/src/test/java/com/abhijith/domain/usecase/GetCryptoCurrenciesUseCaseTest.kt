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

class GetCryptoCurrenciesUseCaseTest {

    private val repository: ICurrencyRepository = mockk()
    private val getCryptoCurrencyUseCase = GetCryptoCurrencyUseCase(repository)

    @Test
    fun `invoke should return list of currencies from repository`() = runBlocking {
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
        coEvery { repository.getCryptoCurrencies() } returns flowOf(currencies)

        val result = getCryptoCurrencyUseCase.invoke().first()

        assertEquals(currencies, result)
        coVerify(exactly = 1) { repository.getCryptoCurrencies() }
    }
}