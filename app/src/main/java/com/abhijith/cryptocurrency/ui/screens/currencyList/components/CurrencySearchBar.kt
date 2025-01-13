package com.abhijith.cryptocurrency.ui.screens.currencyList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.abhijith.cryptocurrency.R

@Composable
fun CurrencySearchBar(
    searchQuery: TextFieldValue,
    onSearchQueryChange: (TextFieldValue) -> Unit,
    onClearSearch: () -> Unit
) {
    Row(
        modifier = Modifier
            .testTag("currency_search_bar")
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (searchQuery.text.isNotEmpty()) {
            IconButton(
                onClick = { onSearchQueryChange(TextFieldValue("")) },
                modifier = Modifier.testTag("back_button")
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.White
                )
            }
        }
        BasicTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .testTag("search_text_field_$searchQuery")
                .weight(1f)
                .padding(horizontal = 8.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                .padding(8.dp)
        )
        if (searchQuery.text.isNotEmpty()) {
            IconButton(onClick = onClearSearch, modifier = Modifier.testTag("clear_button")) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.clear),
                    tint = Color.White
                )
            }
        }
    }
}