package com.abhijith.domain.di

import com.abhijith.domain.repository.ICurrencyRepository
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

class DomainModuleTest : KoinTest {
    @Before
    fun setUp() {
        val testModule = module {
            single<ICurrencyRepository> { mockk() }
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
    fun checkAllModules() {
        val repository: ICurrencyRepository = get()
        assertNotNull(repository)
    }
}