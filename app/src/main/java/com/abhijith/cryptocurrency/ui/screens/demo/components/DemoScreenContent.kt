package com.abhijith.cryptocurrency.ui.screens.demo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.abhijith.cryptocurrency.R

@Composable
fun DemoScreenContent(
    actionHandler: DemoScreenActionHandler,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DemoActionButton(
            title = stringResource(id = R.string.insert_data),
            onClickAction = actionHandler::onInsertData
        )
        Spacer(modifier = Modifier.size(8.dp))
        DemoActionButton(
            title = stringResource(id = R.string.delete_data),
            onClickAction = actionHandler::onClearDatabase
        )
        Spacer(modifier = Modifier.size(8.dp))
        DemoActionButton(
            title = stringResource(id = R.string.show_crypto),
            onClickAction = actionHandler::onShowCrypto
        )
        Spacer(modifier = Modifier.size(8.dp))
        DemoActionButton(
            title = stringResource(id = R.string.show_fiat),
            onClickAction = actionHandler::onShowFiat
        )
        Spacer(modifier = Modifier.size(8.dp))
        DemoActionButton(
            title = stringResource(id = R.string.show_all),
            onClickAction = actionHandler::onShowAll
        )
    }
}