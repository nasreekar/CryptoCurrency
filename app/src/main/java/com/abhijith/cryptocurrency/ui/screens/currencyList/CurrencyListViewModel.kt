package com.abhijith.cryptocurrency.ui.screens.currencyList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.cryptocurrency.R
import com.abhijith.cryptocurrency.ui.screens.CurrencyType
import com.abhijith.domain.model.Currency
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUseCase
import com.abhijith.domain.usecase.GetFiatCurrencyUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CurrencyListViewModel(
    private val getCryptoCurrencyUseCase: GetCryptoCurrencyUseCase,
    private val getFiatCurrencyUseCase: GetFiatCurrencyUseCase,
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase
) : ViewModel() {

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies: StateFlow<List<Currency>> = _currencies

    private val _uiState = MutableStateFlow<CurrencyListState>(CurrencyListState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadCurrencies(type: CurrencyType) {
        viewModelScope.launch {
            val useCase: () -> Flow<List<Currency>> = when (type) {
                CurrencyType.CRYPTO -> getCryptoCurrencyUseCase::invoke
                CurrencyType.FIAT -> getFiatCurrencyUseCase::invoke
                CurrencyType.ALL -> getAllCurrenciesUseCase::invoke
            }

            try {
                useCase()
                    .onStart {
                        _uiState.value = CurrencyListState.Loading
                        delay(1000)
                    }
                    .collectLatest { result ->
                        if (result.isEmpty()) {
                            _uiState.value = CurrencyListState.Empty
                        } else {
                            _currencies.value = result
                            _uiState.value = CurrencyListState.Success
                        }
                    }
            } catch (e: Exception) {
                _uiState.value = CurrencyListState.Error(
                    R.string.loading_currency_list_error,
                    listOf(e.message.orEmpty())
                )
            }
        }
    }
}