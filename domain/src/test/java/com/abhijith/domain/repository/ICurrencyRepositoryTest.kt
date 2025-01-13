package com.abhijith.domain.repository

import com.abhijith.domain.model.Currency
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ICurrencyRepositoryTest {

    private val repository: ICurrencyRepository = mockk()

    @Test
    fun `getAllCurrencies should return list of all currencies`() = runBlocking {
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
    fun `getCryptoCurrencies should return list of crypto currencies`() = runBlocking {
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
    fun `getFiatCurrencies should return list of fiat currencies`() = runBlocking {
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
    fun `clearCurrencies should clear all currencies`() = runBlocking {
        coEvery { repository.clearCurrencies() } returns Unit

        repository.clearCurrencies()

        coVerify(exactly = 1) { repository.clearCurrencies() }
    }

    @Test
    fun `loadAndInsertCurrencies should load and insert currencies`() = runBlocking {
        coEvery { repository.loadAndInsertCurrencies() } returns Unit

        repository.loadAndInsertCurrencies()

        coVerify(exactly = 1) { repository.loadAndInsertCurrencies() }
    }
}