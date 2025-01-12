package com.abhijith.cryptocurrency.ui.screens.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUserCase
import com.abhijith.domain.usecase.GetFiatCurrencyUserCase
import com.abhijith.domain.usecase.InsertCurrenciesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DemoViewModel(
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val getCryptoCurrencyUserCase: GetCryptoCurrencyUserCase,
    private val getFiatCurrencyUserCase: GetFiatCurrencyUserCase,
    private val clearCurrenciesUseCase: ClearCurrenciesUseCase,
    private val insertCurrenciesUseCase: InsertCurrenciesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DemoUiState>(DemoUiState.Initial)
    val uiState = _uiState.asStateFlow()

    /*  init {
          viewModelScope.launch {
              getAllCurrenciesUseCase().collect {
                  Log.d("DemoViewModel", "Currencies: $it")
                  _dummyData.value = "All currencies list: ${it.size}"
              }
          }
      }*/

    fun clearCurrencies() {
        viewModelScope.launch {
            try {
                _uiState.value = DemoUiState.Loading
                clearCurrenciesUseCase()
                _uiState.value = DemoUiState.Success("Data deleted successfully")
            } catch (e: Exception) {
                _uiState.value = DemoUiState.Error("Failed to delete data: ${e.message}")
            }
        }
    }

    fun insertData() {
        viewModelScope.launch {
            try {
                _uiState.value = DemoUiState.Loading

                // Load both JSON files
                // val cryptoJson = loadJsonUseCase.loadJsonFromAssets("crypto_currencies.json")
                // val fiatJson = loadJsonUseCase.loadJsonFromAssets("fiat_currencies.json")

                // Parse JSON using Kotlinx Serialization
                // val cryptoCurrencies = Json.decodeFromString<List<Currency>>(cryptoJson)
                // val fiatCurrencies = Json.decodeFromString<List<Currency>>(fiatJson)

                // Combine and insert all currencies
                // val allCurrencies = cryptoCurrencies + fiatCurrencies
                insertCurrenciesUseCase(emptyList())

                _uiState.value = DemoUiState.Success("Data inserted successfully")
            } catch (e: Exception) {
                _uiState.value = DemoUiState.Error("Failed to insert data: ${e.message}")
            }
        }
    }

    fun showCryptoCurrencies() {
        viewModelScope.launch {
            getCryptoCurrencyUserCase()
                .onStart { _uiState.value = DemoUiState.Loading }
                .catch {
                    _uiState.value =
                        DemoUiState.Error(it.message ?: "Error loading crypto currencies")
                }
                .collect { currencies ->
                    _uiState.value = DemoUiState.NavigateToCurrencyList(currencies)
                }
        }
    }

    fun showFiatCurrencies() {
        viewModelScope.launch {
            getFiatCurrencyUserCase()
                .onStart { _uiState.value = DemoUiState.Loading }
                .catch {
                    _uiState.value =
                        DemoUiState.Error(it.message ?: "Error loading fiat currencies")
                }
                .collect { currencies ->
                    _uiState.value = DemoUiState.NavigateToCurrencyList(currencies)
                }
        }
    }

    fun showAllCurrencies() {
        viewModelScope.launch {
            getAllCurrenciesUseCase()
                .onStart { _uiState.value = DemoUiState.Loading }
                .catch {
                    _uiState.value = DemoUiState.Error(it.message ?: "Error loading all currencies")
                }
                .collect { currencies ->
                    _uiState.value = DemoUiState.NavigateToCurrencyList(currencies)
                }
        }
    }
}