package com.abhijith.cryptocurrency.ui.screens.demo

sealed class DemoUiState {
    data object Initial : DemoUiState()
    data object Loading : DemoUiState()
    data class Success(val messageResId: Int) : DemoUiState()
    data class Error(
        val messageResId: Int,
        val formatArgs: List<String> = emptyList()
    ) : DemoUiState()
    data class NavigateToCurrencyList(val type: String) : DemoUiState()
}
