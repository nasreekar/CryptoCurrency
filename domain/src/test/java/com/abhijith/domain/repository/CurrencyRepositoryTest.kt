package com.abhijith.domain.repository

import com.abhijith.domain.model.Currency
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyRepositoryTest {

    private val repository: CurrencyRepository = mockk()

    @Test
    fun `verify getAllCurrencies should return list of all currencies`() = runTest {
        val currencies = listOf(
            Currency("BTC", "Bitcoin", "BTC"),
            Currency("ETH", "Ethereum Classic", "ETH"),
            Currency("USD", "US Dollar", "$", "USD")
        )
        coEvery { repository.getAllCurrencies() } returns flowOf(currencies)

        val result = repository.getAllCurrencies().first()

        assertEquals(currencies, result)
        coVerify(exactly = 1) { repository.getAllCurrencies() }
    }

    @Test
    fun `verify getCryptoCurrencies should return list of crypto currencies`() = runTest {
        val currencies = listOf(
            Currency("BTC", "Bitcoin", "BTC"),
            Currency("ETH", "Ethereum Classic", "ETH"),
            Currency("USD", "US Dollar", "$", "USD")
        )
        coEvery { repository.getCryptoCurrencies() } returns flowOf(currencies)

        val result = repository.getCryptoCurrencies().first()

        assertEquals(currencies, result)
        coVerify(exactly = 1) { repository.getCryptoCurrencies() }
    }

    @Test
    fun `verify getFiatCurrencies should return list of fiat currencies`() = runTest {
        val currencies = listOf(
            Currency("BTC", "Bitcoin", "BTC"),
            Currency("ETH", "Ethereum Classic", "ETH"),
            Currency("USD", "US Dollar", "$", "USD")
        )
        coEvery { repository.getFiatCurrencies() } returns flowOf(currencies)

        val result = repository.getFiatCurrencies().first()

        assertEquals(currencies, result)
        coVerify(exactly = 1) { repository.getFiatCurrencies() }
    }

    @Test
    fun `verify clearCurrencies should clear all currencies`() = runTest {
        coEvery { repository.clearCurrencies() } returns Unit

        repository.clearCurrencies()

        coVerify(exactly = 1) { repository.clearCurrencies() }
    }

    @Test
    fun `verify loadAndInsertCurrencies should load and insert currencies`() = runTest {
        coEvery { repository.loadAndInsertCurrencies() } returns Unit

        repository.loadAndInsertCurrencies()

        coVerify(exactly = 1) { repository.loadAndInsertCurrencies() }
    }
}