package com.abhijith.cryptocurrency.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import com.abhijith.cryptocurrency.R

@Composable
fun CryptoSnackBar(message: String, onDismiss: () -> Unit) {
    Snackbar(
        action = {
            TextButton(onClick = onDismiss, modifier = Modifier.testTag("crypto_snackbar_dismiss")) {
                Text(text = getString(LocalContext.current, R.string.dismiss))
            }
        },
        modifier = Modifier.padding(16.dp).testTag("crypto_snackbar")
    ) {
        Text(text = message)
    }
}