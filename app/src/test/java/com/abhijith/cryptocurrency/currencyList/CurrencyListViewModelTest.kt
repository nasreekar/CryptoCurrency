package com.abhijith.cryptocurrency.currencyList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.cryptocurrency.ui.screens.currencyList.CurrencyListState
import com.abhijith.cryptocurrency.ui.screens.currencyList.CurrencyListViewModel
import com.abhijith.domain.model.Currency
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUseCase
import com.abhijith.domain.usecase.GetFiatCurrencyUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CurrencyListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: CurrencyListViewModel
    private val getCryptoCurrencyUseCase: GetCryptoCurrencyUseCase = mockk()
    private val getFiatCurrencyUseCase: GetFiatCurrencyUseCase = mockk()
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CurrencyListViewModel(
            getCryptoCurrencyUseCase,
            getFiatCurrencyUseCase,
            getAllCurrenciesUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify loadCurrencies sets Loading state on loading`() = runTest {
        coEvery { getCryptoCurrencyUseCase() } returns flowOf(emptyList())

        viewModel.loadCurrencies(CurrencyType.CRYPTO)

        assertEquals(CurrencyListState.Loading, viewModel.uiState.value)

        advanceUntilIdle()

        assertEquals(CurrencyListState.Empty, viewModel.uiState.value)
        coVerify { getCryptoCurrencyUseCase() }
    }

    @Test
    fun `verify loadCurrencies sets Success state on success`() = runTest {
        val currencies =
            listOf(
                Currency("BTC", "Bitcoin", "BTC"),
                Currency("ETH", "Ethereum Classic", "ETH")
            )
        coEvery { getCryptoCurrencyUseCase() } returns flowOf(currencies)

        viewModel.loadCurrencies(CurrencyType.CRYPTO)

        advanceUntilIdle()

        assertEquals(CurrencyListState.Success, viewModel.uiState.value)
        assertEquals(currencies, viewModel.currencies.value)
        coVerify { getCryptoCurrencyUseCase() }
    }

    @Test
    fun `verify loadCurrencies sets Empty state when no currencies`() = runTest {
        coEvery { getCryptoCurrencyUseCase() } returns flowOf(emptyList())

        viewModel.loadCurrencies(CurrencyType.CRYPTO)

        advanceUntilIdle()

        assertEquals(CurrencyListState.Empty, viewModel.uiState.value)
        assertTrue(viewModel.currencies.value.isEmpty())
        coVerify { getCryptoCurrencyUseCase() }
    }

    @Test
    fun `verify loadCurrencies sets Error state on failure`() = runTest {
        val errorMessage = "Error"
        coEvery { getCryptoCurrencyUseCase() } returns flow {
            throw Exception(errorMessage)
        }

        viewModel.loadCurrencies(CurrencyType.CRYPTO)

        advanceUntilIdle()

        assertEquals(
            CurrencyListState.Error(R.string.loading_currency_list_error, listOf(errorMessage)),
            viewModel.uiState.value
        )
        coVerify { getCryptoCurrencyUseCase() }
    }
}