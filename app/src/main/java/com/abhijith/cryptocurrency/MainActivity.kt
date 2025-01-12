package com.abhijith.cryptocurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.abhijith.cryptocurrency.ui.navigation.AppNavHost
import com.abhijith.cryptocurrency.ui.screens.demo.DemoScreen
import com.abhijith.cryptocurrency.ui.theme.CryptoCurrencyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCurrencyTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Black)
                            .padding(it)
                    ) {
                        AppNavHost()
                    }
                }
            }
        }
    }
}