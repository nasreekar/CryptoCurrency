package com.abhijith.cryptocurrency.ui.screens.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.LoadAndInsertAssetsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DemoViewModel(
    private val clearCurrenciesUseCase: ClearCurrenciesUseCase,
    private val loadAndInsertAssetsUseCase: LoadAndInsertAssetsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DemoUiState>(DemoUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun clearCurrencies() {
        viewModelScope.launch {
            try {
                _uiState.value = DemoUiState.Loading
                clearCurrenciesUseCase()
                _uiState.value = DemoUiState.Success(R.string.data_deletion_success)
            } catch (e: Exception) {
                _uiState.value =
                    DemoUiState.Error(R.string.data_deletion_error, listOf(e.message.orEmpty()))
            }
        }
    }

    fun insertData() {
        viewModelScope.launch {
            try {
                _uiState.value = DemoUiState.Loading
                loadAndInsertAssetsUseCase()
                _uiState.value = DemoUiState.Success(R.string.data_insertion_success)
            } catch (e: Exception) {
                _uiState.value =
                    DemoUiState.Error(R.string.data_insertion_error, listOf(e.message.orEmpty()))
            }
        }
    }

    fun showCryptoCurrencies() {
        _uiState.value = DemoUiState.NavigateToCurrencyList(CurrencyType.CRYPTO.value)
    }

    fun showFiatCurrencies() {
        _uiState.value = DemoUiState.NavigateToCurrencyList(CurrencyType.FIAT.value)
    }

    fun showAllCurrencies() {
        _uiState.value = DemoUiState.NavigateToCurrencyList(CurrencyType.ALL.value)
    }

    fun resetUiState() {
        _uiState.value = DemoUiState.Initial
    }
}