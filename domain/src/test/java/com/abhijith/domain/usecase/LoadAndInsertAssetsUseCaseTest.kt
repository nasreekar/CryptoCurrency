package com.abhijith.domain.usecase

import com.abhijith.domain.repository.ICurrencyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoadAndInsertAssetsUseCaseTest {

    private val repository: ICurrencyRepository = mockk()
    private val loadAndInsertAssetsUseCase = LoadAndInsertAssetsUseCase(repository)

    @Test
    fun `invoke should call loadAndInsertCurrencies on repository`() = runBlocking {
        coEvery { repository.loadAndInsertCurrencies() } returns Unit
        loadAndInsertAssetsUseCase.invoke()
        coVerify(exactly = 1) { repository.loadAndInsertCurrencies() }
    }
}