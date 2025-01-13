package com.abhijith.cryptocurrency.utils

import com.abhijith.cryptocurrency.ui.utils.matchesSearchCriteria
import com.abhijith.domain.model.Currency
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsTest {
    private val currencies = listOf(
        Currency(id = "FOO", name = "Foobar", symbol = "FOO"),
        Currency(id = "BAR", name = "Barfoo", symbol = "BAR"),
        Currency(id = "ETH", name = "Ethereum", symbol = "ETH"),
        Currency(id = "ETC", name = "Ethereum Classic", symbol = "ETC"),
        Currency(id = "TRX", name = "Tronclassic", symbol = "TRX"),
        Currency(id = "BTC", name = "Bitcoin", symbol = "BTC"),
        Currency(id = "ETN", name = "ETN Coin", symbol = "ETN")
    )

    @Test
    fun `test name starts with search term - foo`() {
        val query = "foo"
        val result = currencies.matchesSearchCriteria(query)
        val expected = listOf(
            Currency(id = "FOO", name = "Foobar", symbol = "FOO"),
        )
        assertEquals(expected, result)
    }

    @Test
    fun `test name starts with search term - Ethereum`() {
        val query = "Ethereum"
        val result = currencies.matchesSearchCriteria(query)
        val expected = listOf(
            Currency(id = "ETH", name = "Ethereum", symbol = "ETH"),
            Currency(id = "ETC", name = "Ethereum Classic", symbol = "ETC")
        )
        assertEquals(expected, result)
    }

    @Test
    fun `test name contains search term with space prefix - Classic`() {
        val query = "Classic"
        val result = currencies.matchesSearchCriteria(query)
        val expected = listOf(
            Currency(id = "ETC", name = "Ethereum Classic", symbol = "ETC")
        )
        assertEquals(expected, result)
    }

    @Test
    fun `test symbol starts with search term - ET`() {
        val query = "ET"
        val result = currencies.matchesSearchCriteria(query)
        val expected = listOf(
            Currency(id = "ETH", name = "Ethereum", symbol = "ETH"),
            Currency(id = "ETC", name = "Ethereum Classic", symbol = "ETC"),
            Currency(id = "ETN", name = "ETN Coin", symbol = "ETN")
        )
        assertEquals(expected, result)
    }
}