package com.abhijith.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhijith.data.dao.CurrencyDao
import com.abhijith.data.entity.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}