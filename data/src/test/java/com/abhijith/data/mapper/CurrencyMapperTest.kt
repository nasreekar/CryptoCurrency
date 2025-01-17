package com.abhijith.data.mapper

import com.abhijith.data.entity.CurrencyEntity
import com.abhijith.domain.model.Currency
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyMapperTest {

    @Test
    fun `verify FiatCurrencyEntity toDomain mapping`() {
        val currencyEntity = CurrencyEntity(
            id = "SGD",
            name = "Singapore Dollar",
            symbol = "$",
            code = "SGD"
        )

        val currency = currencyEntity.toDomain()

        assertEquals(currencyEntity.id, currency.id)
        assertEquals(currencyEntity.name, currency.name)
        assertEquals(currencyEntity.symbol, currency.symbol)
        assertEquals(currencyEntity.code, currency.code)
    }

    @Test
    fun `verify FiatCurrency toEntity mapping`() {
        val currency = Currency(
            id = "SGD",
            name = "Singapore Dollar",
            symbol = "$",
            code = "SGD"
        )

        val currencyEntity = currency.toEntity()

        assertEquals(currency.id, currencyEntity.id)
        assertEquals(currency.name, currencyEntity.name)
        assertEquals(currency.symbol, currencyEntity.symbol)
        assertEquals(currency.code, currencyEntity.code)
    }

    @Test
    fun `test CryptoCurrencyEntity toDomain mapping`() {
        val currencyEntity = CurrencyEntity(
            id = "BTC",
            name = "Bitcoin",
            symbol = "BTC"
        )

        val currency = currencyEntity.toDomain()

        assertEquals(currencyEntity.id, currency.id)
        assertEquals(currencyEntity.name, currency.name)
        assertEquals(currencyEntity.symbol, currency.symbol)
        assertEquals(currencyEntity.code, currency.code)
    }

    @Test
    fun `verify CryptoCurrency toEntity mapping`() {
        val currency = Currency(
            id = "BTC",
            name = "Bitcoin",
            symbol = "BTC"
        )

        val currencyEntity = currency.toEntity()

        assertEquals(currency.id, currencyEntity.id)
        assertEquals(currency.name, currencyEntity.name)
        assertEquals(currency.symbol, currencyEntity.symbol)
        assertEquals(currency.code, currencyEntity.code)
    }
}