package com.abhijith.cryptocurrency.ui.screens.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.components.DemoScreenAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun DemoScreen(viewModel: DemoViewModel = koinViewModel()) {
    val data by viewModel.dummyData.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DemoScreenAction(
            title = getString(
                LocalContext.current, R.string.insert_data
            )
        ) {
        }
        Spacer(modifier = Modifier.size(8.dp))
        DemoScreenAction(
            title = getString(
                LocalContext.current, R.string.delete_data
            )
        ) {
        }
        Spacer(modifier = Modifier.size(8.dp))
        DemoScreenAction(
            title = getString(
                LocalContext.current, R.string.show_crypto
            )
        ) {
        }
        Spacer(modifier = Modifier.size(8.dp))
        DemoScreenAction(
            title = getString(
                LocalContext.current, R.string.show_fiat
            )
        ) {
        }
        Spacer(modifier = Modifier.size(8.dp))
        DemoScreenAction(
            title = getString(
                LocalContext.current, R.string.show_all
            )
        ) {
        }
    }
}

@Composable
@Preview
fun DemoScreenPreview() {
    DemoScreen()
}