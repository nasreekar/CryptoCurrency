package com.abhijith.domain.di

import com.abhijith.domain.interactor.CurrencyUseCaseInteractor
import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUseCase
import com.abhijith.domain.usecase.GetFiatCurrencyUseCase
import com.abhijith.domain.usecase.LoadAndInsertAssetsUseCase
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNotNull

class DomainModuleTest : KoinTest {
    @Before
    fun setUp() {
        val testModule = module {
            single { mockk<ClearCurrenciesUseCase>(relaxed = true) }
            single { mockk<LoadAndInsertAssetsUseCase>(relaxed = true) }
            single { mockk<GetCryptoCurrencyUseCase>(relaxed = true) }
            single { mockk<GetFiatCurrencyUseCase>(relaxed = true) }
            single { mockk<GetAllCurrenciesUseCase>(relaxed = true) }
            single { mockk<CurrencyUseCaseInteractor>(relaxed = true) }
        }

        startKoin {
            modules(domainModule, testModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `verify ClearCurrenciesUseCase is provided`() {
        val clearCurrenciesUseCase: ClearCurrenciesUseCase by inject()
        assertNotNull(clearCurrenciesUseCase)
    }

    @Test
    fun `verify LoadAndInsertAssetsUseCase is provided`() {
        val loadAndInsertAssetsUseCase: LoadAndInsertAssetsUseCase by inject()
        assertNotNull(loadAndInsertAssetsUseCase)
    }

    @Test
    fun `verify GetCryptoCurrencyUseCase is provided`() {
        val getCryptoCurrencyUseCase: GetCryptoCurrencyUseCase by inject()
        assertNotNull(getCryptoCurrencyUseCase)
    }

    @Test
    fun `verify GetFiatCurrencyUseCase is provided`() {
        val getFiatCurrencyUseCase: GetFiatCurrencyUseCase by inject()
        assertNotNull(getFiatCurrencyUseCase)
    }

    @Test
    fun `verify GetAllCurrenciesUseCase is provided`() {
        val getAllCurrenciesUseCase: GetAllCurrenciesUseCase by inject()
        assertNotNull(getAllCurrenciesUseCase)
    }

    @Test
    fun `verify CurrencyUseCaseInteractor is provided`() {
        val currencyUseCaseInteractor: CurrencyUseCaseInteractor by inject()
        assertNotNull(currencyUseCaseInteractor)
    }
}