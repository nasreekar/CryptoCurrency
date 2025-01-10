package com.abhijith.domain.model

data class Currency(
    val id: String,
    val name: String,
    val symbol: String,
    val code: String? = null
)