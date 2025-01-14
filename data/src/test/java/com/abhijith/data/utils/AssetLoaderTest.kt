package com.abhijith.data.utils

import android.content.Context
import android.content.res.AssetManager
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.InputStream

class AssetLoaderTest {
    private val context: Context = mockk()
    private val assetManager: AssetManager = mockk()
    private val assetLoader: AssetLoader = AndroidAssetLoader(context)

    @Test
    fun `verify loadJsonFromAssets returns correct string`() {
        val fileName = "test.json"
        val jsonContent =
            """[{"id": "BTC","name": "Bitcoin","symbol": "BTC"},{"id": "ETH","name": "Ethereum","symbol": "ETH"}]"""
        val inputStream: InputStream = ByteArrayInputStream(jsonContent.toByteArray())

        every { context.assets } returns assetManager
        every { assetManager.open(fileName) } returns inputStream

        val result = assetLoader.loadJsonFromAssets(fileName)
        assertEquals(jsonContent, result)

        verify { assetManager.open(fileName) }
    }
}