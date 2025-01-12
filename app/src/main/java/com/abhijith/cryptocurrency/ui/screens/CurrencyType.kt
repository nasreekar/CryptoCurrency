package com.abhijith.cryptocurrency.ui.screens

enum class CurrencyType(val value: String) {
    ALL("All"),
    CRYPTO("Crypto"),
    FIAT("Fiat");

    companion object {
        fun fromValue(value: String): CurrencyType {
            return entries.find { it.value.equals(value, ignoreCase = true) } ?: ALL
        }
    }
}