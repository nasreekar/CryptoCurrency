package com.abhijith.cryptocurrency.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.abhijith.cryptocurrency.R

@Composable
fun ErrorScreen(imgRes: Int, message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imgRes),
                contentDescription = stringResource(id = R.string.search),
                modifier = Modifier.size(72.dp)
            )
            Text(
                text = message,
                color = Color.White,
                modifier = Modifier.padding(24.dp, 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}