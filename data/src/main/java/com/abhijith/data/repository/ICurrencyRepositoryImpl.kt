package com.abhijith.data.repository

import com.abhijith.data.dao.CurrencyDao
import com.abhijith.data.entity.CurrencyEntity
import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ICurrencyRepositoryImpl(private val dao: CurrencyDao) : ICurrencyRepository {

    override fun getCurrencies(): Flow<List<Currency>> {
        return dao.getAllCurrencies().map { entities ->
            entities.map { entity ->
                Currency(
                    id = entity.id,
                    name = entity.name,
                    symbol = entity.symbol,
                    code = entity.code
                )
            }
        }
    }

    override fun getCryptoCurrencies(): Flow<List<Currency>> {
        return dao.getCryptoCurrencies().map { entities ->
            entities.map { entity ->
                Currency(
                    id = entity.id,
                    name = entity.name,
                    symbol = entity.symbol,
                    code = null
                )
            }
        }
    }

    override fun getFiatCurrencies(): Flow<List<Currency>> {
        return dao.getFiatCurrencies().map { entities ->
            entities.map { entity ->
                Currency(
                    id = entity.id,
                    name = entity.name,
                    symbol = entity.symbol,
                    code = entity.code
                )
            }
        }
    }

    override suspend fun insertCurrencies(currencies: List<Currency>) {
        val entities = currencies.map { currency ->
            CurrencyEntity(
                id = currency.id,
                name = currency.name,
                symbol = currency.symbol,
                code = currency.code
            )
        }
        dao.insertCurrencies(entities)
    }

    override suspend fun clearCurrencies() = dao.clearCurrencies()
}