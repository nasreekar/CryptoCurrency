package com.abhijith.domain.interactor

import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUseCase
import com.abhijith.domain.usecase.GetFiatCurrencyUseCase
import com.abhijith.domain.usecase.LoadAndInsertAssetsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CurrencyUseCaseInteractorTest {

    private lateinit var interactor: CurrencyUseCaseInteractor
    private val getCryptoCurrencyUseCase: GetCryptoCurrencyUseCase = mockk()
    private val getFiatCurrencyUseCase: GetFiatCurrencyUseCase = mockk()
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase = mockk()
    private val clearCurrenciesUseCase: ClearCurrenciesUseCase = mockk()
    private val loadAndInsertAssetsUseCase: LoadAndInsertAssetsUseCase = mockk()

    @Before
    fun setUp() {
        interactor = CurrencyUseCaseInteractor(
            getCryptoCurrencyUseCase,
            getFiatCurrencyUseCase,
            getAllCurrenciesUseCase,
            clearCurrenciesUseCase,
            loadAndInsertAssetsUseCase
        )
    }

    @Test
    fun `verify getCryptoCurrencies calls GetCryptoCurrencyUseCase`() {
        coEvery { getCryptoCurrencyUseCase.invoke() } returns flowOf(emptyList())
        interactor.getCryptoCurrencies()
        coVerify { getCryptoCurrencyUseCase.invoke() }
    }

    @Test
    fun `verify getFiatCurrencies calls GetFiatCurrencyUseCase`() {
        coEvery { getFiatCurrencyUseCase.invoke() } returns flowOf(emptyList())
        interactor.getFiatCurrencies()
        coVerify { getFiatCurrencyUseCase.invoke() }
    }

    @Test
    fun `verify getAllCurrencies calls GetAllCurrenciesUseCase`() {
        coEvery { getAllCurrenciesUseCase.invoke() } returns flowOf(emptyList())
        interactor.getAllCurrencies()
        coVerify { getAllCurrenciesUseCase.invoke() }
    }

    @Test
    fun `verify clearCurrencies calls ClearCurrenciesUseCase`() = runTest {
        coEvery { clearCurrenciesUseCase.invoke() } returns Unit
        interactor.clearCurrencies()
        coVerify { clearCurrenciesUseCase.invoke() }
    }

    @Test
    fun `verify loadAndInsertAssets calls LoadAndInsertAssetsUseCase`() = runTest {
        coEvery { loadAndInsertAssetsUseCase.invoke() } returns Unit
        interactor.loadAndInsertAssets()
        coVerify { loadAndInsertAssetsUseCase.invoke() }
    }
}