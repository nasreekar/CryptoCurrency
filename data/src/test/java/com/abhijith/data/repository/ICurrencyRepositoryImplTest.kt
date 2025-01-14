package com.abhijith.data.repository

import com.abhijith.data.dao.CurrencyDao
import com.abhijith.data.entity.CurrencyEntity
import com.abhijith.data.utils.AssetLoader
import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class ICurrencyRepositoryImplTest {

    private val dao = mockk<CurrencyDao>(relaxed = true)
    private val assetLoader: AssetLoader = mockk()
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    private val allCurrencies = listOf(
        CurrencyEntity("BTC", "Bitcoin", "BTC"),
        CurrencyEntity("ETH", "Ethereum Classic", "ETH"),
        CurrencyEntity("USD", "US Dollar", "$", "USD")
    )

    private lateinit var repository: ICurrencyRepository

    @Before
    fun setUp() {
        val daoFlow = MutableStateFlow<List<CurrencyEntity>>(emptyList())
        every { dao.getAllCurrencies() } returns daoFlow
        repository = ICurrencyRepositoryImpl(dao, dispatcher, assetLoader)
    }

    @After
    fun tearDown() {
        scope.cancel()
    }

    @Test
    fun `verify getAllCurrencies returns mapped currencies`() = scope.runTest {
        val daoFlow = dao.getAllCurrencies() as MutableStateFlow
        daoFlow.emit(allCurrencies)
        every { dao.getAllCurrencies() } returns daoFlow

        val result = repository.getAllCurrencies().first { it.isNotEmpty() }

        assertEquals(
            listOf(
                Currency("BTC", "Bitcoin", "BTC"),
                Currency("ETH", "Ethereum Classic", "ETH"),
                Currency("USD", "US Dollar", "$", "USD")
            ),
            result
        )

        advanceUntilIdle()
    }

    @Test
    fun `verify getCryptoCurrencies returns only crypto currencies`() = scope.runTest {
        val daoFlow = dao.getAllCurrencies() as MutableStateFlow
        daoFlow.emit(allCurrencies)
        every { dao.getAllCurrencies() } returns daoFlow

        val result = repository.getCryptoCurrencies().first { it.isNotEmpty() }

        assertEquals(
            listOf(
                Currency("BTC", "Bitcoin", "BTC"),
                Currency("ETH", "Ethereum Classic", "ETH")
            ),
            result
        )

        advanceUntilIdle()
    }

    @Test
    fun `verify getFiatCurrencies returns only fiat currencies`() = scope.runTest {
        val daoFlow = dao.getAllCurrencies() as MutableStateFlow
        daoFlow.emit(allCurrencies)
        every { dao.getAllCurrencies() } returns daoFlow

        val result = repository.getFiatCurrencies().first { it.isNotEmpty() }

        assertEquals(listOf(Currency("USD", "US Dollar", "$", "USD")), result)

        advanceUntilIdle()
    }

    @Test
    fun `verify clearCurrencies clears the currencies`() = scope.runTest {
        coEvery { dao.clearCurrencies() } just Runs

        repository.clearCurrencies()

        coVerify { dao.clearCurrencies() }
    }

    @Test
    fun `verify loadAndInsertCurrencies loads and inserts currencies`() = scope.runTest {
        val cryptoJson = """[{"id": "BTC", "name": "Bitcoin", "symbol": "BTC"}]"""
        val fiatJson = """[{"id": "USD", "name": "US Dollar", "symbol": "$", "code": "USD"}]"""
        val cryptoEntities = listOf(CurrencyEntity("BTC", "Bitcoin", "BTC"))
        val fiatEntities = listOf(CurrencyEntity("USD", "US Dollar", "$", "USD"))

        coEvery { assetLoader.loadJsonFromAssets("crypto.json") } returns cryptoJson
        coEvery { assetLoader.loadJsonFromAssets("fiat.json") } returns fiatJson
        coEvery { dao.clearCurrencies() } just Runs
        coEvery { dao.insertCurrencies(any()) } just Runs
        coEvery { dao.getAllCurrencies() } returns flowOf(cryptoEntities + fiatEntities)

        repository.loadAndInsertCurrencies()

        coVerify {
            assetLoader.loadJsonFromAssets("crypto.json")
            assetLoader.loadJsonFromAssets("fiat.json")
            dao.clearCurrencies()
            dao.insertCurrencies(cryptoEntities + fiatEntities)
        }
    }

    @Test
    fun `verify loadAndInsertCurrencies throws RuntimeException on failure`() = scope.runTest {
        coEvery { assetLoader.loadJsonFromAssets(any()) } throws Exception("Error with the json")

        val exception = assertFailsWith<RuntimeException> {
            repository.loadAndInsertCurrencies()
        }

        assertEquals("Failed to load and insert currencies: Error with the json", exception.message)
    }
}