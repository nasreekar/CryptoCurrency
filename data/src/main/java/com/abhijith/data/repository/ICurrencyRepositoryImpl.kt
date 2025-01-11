package com.abhijith.data.repository

import com.abhijith.data.dao.CurrencyDao
import com.abhijith.data.mapper.toDomain
import com.abhijith.data.mapper.toEntity
import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ICurrencyRepositoryImpl(private val dao: CurrencyDao) : ICurrencyRepository {

    override fun getAllCurrencies(): Flow<List<Currency>> {
        return dao.getAllCurrencies().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getCryptoCurrencies(): Flow<List<Currency>> {
        return dao.getCryptoCurrencies().map { entities ->
            entities.map {
                it.toDomain().copy(code = null)
            }
        }
    }

    override fun getFiatCurrencies(): Flow<List<Currency>> {
        return dao.getFiatCurrencies().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertCurrencies(currencies: List<Currency>) {
        dao.insertCurrencies(currencies.map {
            it.toEntity()
        })
    }

    override suspend fun clearCurrencies() = dao.clearCurrencies()
}