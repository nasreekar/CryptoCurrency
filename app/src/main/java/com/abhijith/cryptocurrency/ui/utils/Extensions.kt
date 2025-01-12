package com.abhijith.cryptocurrency.ui.utils

import android.content.Context
import com.abhijith.domain.model.Currency

fun Int.loadFromStringRes(context: Context): String {
    return context.getString(this)
}

fun List<Currency>.matchesSearchCriteria(query: String): List<Currency> {
    return this.filter {
        it.name.startsWith(query, ignoreCase = true).or(
            it.name.contains(" $query", ignoreCase = true)
        ).or(
            it.symbol.startsWith(query, ignoreCase = true)
        )
    }
}