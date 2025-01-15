package com.abhijith.cryptocurrency.ui.screens.currencyList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.domain.interactor.CurrencyUseCaseInteractor
import com.abhijith.domain.model.Currency
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CurrencyListViewModel(
    private val currencyUseCaseInteractor: CurrencyUseCaseInteractor
) : ViewModel() {

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies: StateFlow<List<Currency>> = _currencies

    private val _uiState = MutableStateFlow<CurrencyListState>(CurrencyListState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadCurrencies(type: CurrencyType) {
        viewModelScope.launch {
            val useCase = when (type) {
                CurrencyType.CRYPTO -> currencyUseCaseInteractor::getCryptoCurrencies
                CurrencyType.FIAT -> currencyUseCaseInteractor::getFiatCurrencies
                CurrencyType.ALL -> currencyUseCaseInteractor::getAllCurrencies
            }

            useCase()
                .onStart {
                    _uiState.value = CurrencyListState.Loading
                    delay(1000)
                }
                .catch { e ->
                    _uiState.value = CurrencyListState.Error(
                        R.string.loading_currency_list_error,
                        listOf(e.message.orEmpty())
                    )
                }
                .collectLatest { result ->
                    _uiState.value = if (result.isEmpty()) {
                        CurrencyListState.Empty
                    } else {
                        _currencies.value = result
                        CurrencyListState.Success
                    }
                }
        }
    }
}