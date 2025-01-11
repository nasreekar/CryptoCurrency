package com.abhijith.data.repository

import com.abhijith.data.dao.CurrencyDao
import com.abhijith.data.mapper.toDomain
import com.abhijith.data.mapper.toEntity
import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ICurrencyRepositoryImpl(
    private val dao: CurrencyDao,
    private val dispatcher: CoroutineDispatcher
) : ICurrencyRepository {

    override fun getAllCurrencies(): Flow<List<Currency>> =
        dao.getAllCurrencies()
            .map { entities -> entities.map { it.toDomain() } }
            .flowOn(dispatcher)

    override fun getCryptoCurrencies(): Flow<List<Currency>> =
        dao.getCryptoCurrencies()
            .map { entities -> entities.map { it.toDomain().copy(code = null) } }
            .flowOn(dispatcher)

    override fun getFiatCurrencies(): Flow<List<Currency>> =
        dao.getFiatCurrencies()
            .map { entities -> entities.map { it.toDomain() } }
            .flowOn(dispatcher)

    override suspend fun insertCurrencies(currencies: List<Currency>) =
        withContext(dispatcher) {
            dao.insertCurrencies(currencies.map { it.toEntity() })
        }

    override suspend fun clearCurrencies() =
        withContext(dispatcher) {
            dao.clearCurrencies()
        }
}