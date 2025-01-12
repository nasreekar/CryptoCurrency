package com.abhijith.data.repository

import com.abhijith.data.dao.CurrencyDao
import com.abhijith.data.entity.CurrencyEntity
import com.abhijith.data.mapper.toDomain
import com.abhijith.data.utils.AssetLoader
import com.abhijith.domain.model.Currency
import com.abhijith.domain.repository.ICurrencyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ICurrencyRepositoryImpl(
    private val dao: CurrencyDao,
    private val dispatcher: CoroutineDispatcher,
    private val assetLoader: AssetLoader
) : ICurrencyRepository {

    private val _allCurrencies = dao.getAllCurrencies()
        .distinctUntilChanged()
        .flowOn(dispatcher)
        .stateIn(
            scope = CoroutineScope(dispatcher + SupervisorJob()),
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    override fun getAllCurrencies(): Flow<List<Currency>> =
        _allCurrencies.map { entities -> entities.map { it.toDomain() } }

    override fun getCryptoCurrencies(): Flow<List<Currency>> =
        _allCurrencies.map { entities ->
            entities
                .filter { it.code == null }
                .map { it.toDomain() }
        }

    override fun getFiatCurrencies(): Flow<List<Currency>> =
        _allCurrencies.map { entities ->
            entities
                .filter { it.code != null }
                .map { it.toDomain() }
        }

    override suspend fun clearCurrencies() =
        withContext(dispatcher) {
            dao.clearCurrencies()
        }

    override suspend fun loadAndInsertCurrencies() =
        withContext(dispatcher) {
            try {
                val cryptoJson = assetLoader.loadJsonFromAssets("crypto.json")
                val fiatJson = assetLoader.loadJsonFromAssets("fiat.json")

                val cryptoCurrencies =
                    Json.decodeFromString<List<CurrencyEntity>>(cryptoJson)
                val fiatCurrencies =
                    Json.decodeFromString<List<CurrencyEntity>>(fiatJson)

                dao.clearCurrencies()
                dao.insertCurrencies(cryptoCurrencies + fiatCurrencies)
            } catch (e: Exception) {
                throw RuntimeException("Failed to load and insert currencies: ${e.message}")
            }
        }
}