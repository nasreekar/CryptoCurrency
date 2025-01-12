package com.abhijith.cryptocurrency.ui.screens.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.components.DemoActionButton

@Composable
fun DemoScreenActions(
    onClearDatabase: () -> Unit,
    onInsertData: () -> Unit,
    onShowCrypto: () -> Unit,
    onShowFiat: () -> Unit,
    onShowAll: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DemoActionButton(
            title = getString(
                LocalContext.current, R.string.insert_data
            )
        ) {
            onInsertData()
        }
        Spacer(modifier = Modifier.size(8.dp))
        DemoActionButton(
            title = getString(
                LocalContext.current, R.string.delete_data
            )
        ) {
            onClearDatabase()
        }
        Spacer(modifier = Modifier.size(8.dp))
        DemoActionButton(
            title = getString(
                LocalContext.current, R.string.show_crypto
            )
        ) {
            onShowCrypto()
        }
        Spacer(modifier = Modifier.size(8.dp))
        DemoActionButton(
            title = getString(
                LocalContext.current, R.string.show_fiat
            )
        ) {
            onShowFiat()
        }
        Spacer(modifier = Modifier.size(8.dp))
        DemoActionButton(
            title = getString(
                LocalContext.current, R.string.show_all
            )
        ) {
            onShowAll()
        }
    }
}