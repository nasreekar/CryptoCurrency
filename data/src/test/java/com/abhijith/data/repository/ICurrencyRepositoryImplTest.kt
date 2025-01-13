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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class ICurrencyRepositoryImplTest {

    private val dao: CurrencyDao = mockk()
    private val assetLoader: AssetLoader = mockk()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var repository: ICurrencyRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        every { dao.getAllCurrencies() } returns flowOf(emptyList())
        repository = ICurrencyRepositoryImpl(dao, dispatcher, assetLoader)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /*@Test
    fun `getAllCurrencies returns mapped currencies`() = runTest {
        val entities = listOf(
            CurrencyEntity("BTC", "Bitcoin", "BTC"),
            CurrencyEntity("ETH", "Ethereum Classic", "ETH"),
            CurrencyEntity("USD", "US Dollar", "$", "USD")
        )
        every { dao.getAllCurrencies() } returns flowOf(entities)
        // using every instead of coEvery as its not a suspend function

        val result = mutableListOf<List<Currency>>()
        val job = launch {
            repository.getAllCurrencies().collect { currencies -> result.add(currencies) }
        }

        advanceUntilIdle()

        assertEquals(
            listOf(
                Currency("BTC", "Bitcoin", "BTC"),
                Currency("ETH", "Ethereum Classic", "ETH"),
                Currency("USD", "US Dollar", "$", "USD")
            ),
            result.last()
        )

        job.cancel()
    }

    @Test
    fun `getCryptoCurrencies returns only crypto currencies`() = runTest {
        val entities = listOf(
            CurrencyEntity("BTC", "Bitcoin", "BTC"),
            CurrencyEntity("USD", "US Dollar", "$", "USD"),
            CurrencyEntity("ETH", "Ethereum Classic", "ETH")
        )
        coEvery { dao.getAllCurrencies() } returns flowOf(entities)

        val result = repository.getCryptoCurrencies().first()

        assertEquals(
            listOf(
                Currency("BTC", "Bitcoin", "BTC"),
                CurrencyEntity("ETH", "Ethereum Classic", "ETH")
            ), result
        )
    }

    @Test
    fun `getFiatCurrencies returns only fiat currencies`() = runTest {
        val entities = listOf(
            CurrencyEntity("BTC", "Bitcoin", "BTC"),
            CurrencyEntity("USD", "US Dollar", "$", "USD")
        )

        coEvery { dao.getAllCurrencies() } returns flowOf(entities)

        val result = repository.getFiatCurrencies().first()

        assertEquals(listOf(Currency("USD", "US Dollar", "$", "USD")), result)
    }*/

    @Test
    fun `clearCurrencies clears the currencies`() = runTest {
        coEvery { dao.clearCurrencies() } just Runs

        repository.clearCurrencies()

        coVerify { dao.clearCurrencies() }
    }

    @Test
    fun `loadAndInsertCurrencies loads and inserts currencies`() = runTest {
        val cryptoJson = """[{"id": "BTC", "name": "Bitcoin", "symbol": "BTC"}]"""
        val fiatJson = """[{"id": "USD", "name": "US Dollar", "symbol": "$", "code": "USD"}]"""
        val cryptoEntities = listOf(CurrencyEntity("BTC", "Bitcoin", "BTC"))
        val fiatEntities = listOf(CurrencyEntity("USD", "US Dollar", "$", "USD"))

        coEvery { assetLoader.loadJsonFromAssets("crypto.json") } returns cryptoJson
        coEvery { assetLoader.loadJsonFromAssets("fiat.json") } returns fiatJson
        coEvery { dao.clearCurrencies() } just Runs
        coEvery { dao.insertCurrencies(any()) } just Runs

        repository.loadAndInsertCurrencies()

        coVerify {
            assetLoader.loadJsonFromAssets("crypto.json")
            assetLoader.loadJsonFromAssets("fiat.json")
            dao.clearCurrencies()
            dao.insertCurrencies(cryptoEntities + fiatEntities)
        }
    }

    @Test
    fun `loadAndInsertCurrencies throws RuntimeException on failure`() = runTest {
        coEvery { assetLoader.loadJsonFromAssets(any()) } throws Exception("Failed to load")

        val exception = assertFailsWith<RuntimeException> {
            repository.loadAndInsertCurrencies()
        }

        assertEquals("Failed to load and insert currencies: Failed to load", exception.message)
    }
}