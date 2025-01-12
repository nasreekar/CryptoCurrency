package com.abhijith.data.utils

import android.content.Context

interface AssetLoader {
    fun loadJsonFromAssets(fileName: String): String
}

class AndroidAssetLoader(private val context: Context) : AssetLoader {
    override fun loadJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
