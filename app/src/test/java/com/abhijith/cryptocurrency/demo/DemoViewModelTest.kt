package com.abhijith.cryptocurrency.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.cryptocurrency.ui.screens.demo.DemoUiState
import com.abhijith.cryptocurrency.ui.screens.demo.DemoViewModel
import com.abhijith.domain.interactor.CurrencyUseCaseInteractor
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


/**
 * runTest creates a coroutine to test the suspend function.
 * however if inside the function a new coroutine is created, it will not wait till it is completed.
 * To fix this, advanceUntilIdle is used to wait till all coroutines are completed.
 */


@ExperimentalCoroutinesApi
class DemoViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: DemoViewModel
    private val useCaseInteractor: CurrencyUseCaseInteractor = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DemoViewModel(useCaseInteractor)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `verify clearCurrencies sets Success state on success`() = runTest {
        coEvery { useCaseInteractor.clearCurrencies() } returns Unit

        viewModel.clearCurrencies()

        advanceUntilIdle()

        assertEquals(DemoUiState.Success(R.string.data_deletion_success), viewModel.uiState.value)
        coVerify { useCaseInteractor.clearCurrencies() }
    }

    @Test
    fun `verify clearCurrencies sets Error state on failure`() = runTest {
        val errorMessage = "Error"
        coEvery { useCaseInteractor.clearCurrencies() } throws Exception(errorMessage)

        viewModel.clearCurrencies()

        advanceUntilIdle()

        assertEquals(
            DemoUiState.Error(R.string.data_deletion_error, listOf(errorMessage)),
            viewModel.uiState.value
        )
        coVerify { useCaseInteractor.clearCurrencies() }
    }

    @Test
    fun `verify insertData sets Success state on success`() = runTest {
        coEvery { useCaseInteractor.loadAndInsertAssets() } returns Unit

        viewModel.insertData()

        advanceUntilIdle()

        assertEquals(DemoUiState.Success(R.string.data_insertion_success), viewModel.uiState.value)
        coVerify { useCaseInteractor.loadAndInsertAssets() }
    }

    @Test
    fun `verify insertData sets Error state on failure`() = runTest {
        val errorMessage = "Error"
        coEvery { useCaseInteractor.loadAndInsertAssets() } throws Exception(errorMessage)

        viewModel.insertData()

        advanceUntilIdle()

        assertEquals(
            DemoUiState.Error(R.string.data_insertion_error, listOf(errorMessage)),
            viewModel.uiState.value
        )
        coVerify { useCaseInteractor.loadAndInsertAssets() }
    }

    @Test
    fun `verify showCryptoCurrencies sets NavigateToCurrencyList state`() {
        viewModel.showCryptoCurrencies()

        assertEquals(
            DemoUiState.NavigateToCurrencyList(CurrencyType.CRYPTO.value),
            viewModel.uiState.value
        )
    }

    @Test
    fun `verify showFiatCurrencies sets NavigateToCurrencyList state`() {
        viewModel.showFiatCurrencies()

        assertEquals(
            DemoUiState.NavigateToCurrencyList(CurrencyType.FIAT.value),
            viewModel.uiState.value
        )
    }

    @Test
    fun `verify showAllCurrencies sets NavigateToCurrencyList state`() {
        viewModel.showAllCurrencies()

        assertEquals(
            DemoUiState.NavigateToCurrencyList(CurrencyType.ALL.value),
            viewModel.uiState.value
        )
    }

    @Test
    fun `verify resetUiState sets Initial state`() {
        viewModel.resetUiState()

        assertEquals(DemoUiState.Initial, viewModel.uiState.value)
    }
}