package com.abhijith.cryptocurrency.ui.screens.demo

import com.abhijith.domain.model.Currency

sealed class DemoUiState {
    data object Initial : DemoUiState()
    data object Loading : DemoUiState()
    data class Success(val message: String) : DemoUiState()
    data class Error(val message: String) : DemoUiState()
    data class NavigateToCurrencyList(val type: String) : DemoUiState()
}
