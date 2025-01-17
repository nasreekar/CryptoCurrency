package com.abhijith.cryptocurrency.di

import com.abhijith.cryptocurrency.ui.screens.currencyList.CurrencyListViewModel
import com.abhijith.cryptocurrency.ui.screens.demo.DemoViewModel
import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUseCase
import com.abhijith.domain.usecase.GetFiatCurrencyUseCase
import com.abhijith.domain.usecase.LoadAndInsertAssetsUseCase
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNotNull

class AppModuleTest : KoinTest {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        val testModule = module {

            single { mockk<ClearCurrenciesUseCase>(relaxed = true) }
            single { mockk<LoadAndInsertAssetsUseCase>(relaxed = true) }
            single { mockk<GetCryptoCurrencyUseCase>(relaxed = true) }
            single { mockk<GetFiatCurrencyUseCase>(relaxed = true) }
            single { mockk<GetAllCurrenciesUseCase>(relaxed = true) }
        }

        startKoin {
            modules(appModule, testModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `verify DemoViewModel is provided`() {
        val demoViewModel: DemoViewModel by inject()
        assertNotNull(demoViewModel)
    }

    @Test
    fun `verify CurrencyListViewModel is provided`() {
        val currencyListViewModel: CurrencyListViewModel by inject()
        assertNotNull(currencyListViewModel)
    }
}

