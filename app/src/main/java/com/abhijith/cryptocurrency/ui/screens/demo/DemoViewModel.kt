package com.abhijith.cryptocurrency.ui.screens.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.domain.interactor.CurrencyUseCaseInteractor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DemoViewModel(
    private val currencyUseCaseInteractor: CurrencyUseCaseInteractor
) : ViewModel() {
    private val _uiState = MutableStateFlow<DemoUiState>(DemoUiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun clearCurrencies() {
        viewModelScope.launch {
            try {
                _uiState.value = DemoUiState.Loading
                currencyUseCaseInteractor.clearCurrencies()
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

                // adding an artificial delay to show the loading state as the data insertion is very fast
                coroutineScope {
                    launch { delay(800) }
                    launch { currencyUseCaseInteractor.loadAndInsertAssets() }
                }

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