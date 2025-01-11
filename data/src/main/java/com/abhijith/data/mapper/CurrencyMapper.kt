package com.abhijith.data.mapper

import com.abhijith.data.entity.CurrencyEntity
import com.abhijith.domain.model.Currency

fun CurrencyEntity.toDomain(): Currency =
    Currency(
        id = id,
        name = name,
        symbol = symbol,
        code = code
    )

fun Currency.toEntity(): CurrencyEntity =
    CurrencyEntity(
        id = id,
        name = name,
        symbol = symbol,
        code = code
    )