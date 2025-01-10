package com.abhijith.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abhijith.data.entity.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency_info")
    fun getAllCurrencies(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency_info WHERE code IS NOT NULL")
    fun getFiatCurrencies(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency_info WHERE code IS NULL")
    fun getCryptoCurrencies(): Flow<List<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<CurrencyEntity>)

    @Query("DELETE FROM currency_info")
    suspend fun clearCurrencies()
}