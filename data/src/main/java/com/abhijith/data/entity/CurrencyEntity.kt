package com.abhijith.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_info")
data class CurrencyEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val code: String? = null
)