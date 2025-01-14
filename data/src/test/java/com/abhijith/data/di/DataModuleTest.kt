package com.abhijith.data.di

import android.content.Context
import androidx.room.Room
import com.abhijith.data.dao.CurrencyDao
import com.abhijith.data.database.CurrencyDatabase
import com.abhijith.data.utils.AssetLoader
import com.abhijith.domain.repository.ICurrencyRepository
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get

class DataModuleTest : KoinTest {

    @Before
    fun setUp() {
        val mockContext = mockk<Context>(relaxed = true)

        val testModule = module {
            single { mockContext }

            single {
                Room.inMemoryDatabaseBuilder(
                    get(),
                    CurrencyDatabase::class.java
                ).allowMainThreadQueries().build()
            }

            single<AssetLoader> { mockk(relaxed = true) }

            single<CoroutineDispatcher> { Dispatchers.Main }
        }

        startKoin {
            modules(dataModule, testModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `verify all dependencies are loaded`() {
        val database: CurrencyDatabase = get()
        val dao: CurrencyDao = get()
        val assetLoader: AssetLoader = get()
        val repository: ICurrencyRepository = get()

        assertNotNull(database)
        assertNotNull(dao)
        assertNotNull(assetLoader)
        assertNotNull(repository)
    }
}
