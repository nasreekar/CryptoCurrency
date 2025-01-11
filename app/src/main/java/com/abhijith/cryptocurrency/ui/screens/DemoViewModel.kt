package com.abhijith.cryptocurrency.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhijith.domain.usecase.ClearCurrenciesUseCase
import com.abhijith.domain.usecase.GetAllCurrenciesUseCase
import com.abhijith.domain.usecase.GetCryptoCurrencyUserCase
import com.abhijith.domain.usecase.GetFiatCurrencyUserCase
import com.abhijith.domain.usecase.InsertCurrenciesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DemoViewModel(
    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase,
    private val getCryptoCurrencyUserCase: GetCryptoCurrencyUserCase,
    private val getFiatCurrencyUserCase: GetFiatCurrencyUserCase,
    private val clearCurrenciesUseCase: ClearCurrenciesUseCase,
    private val insertCurrenciesUseCase: InsertCurrenciesUseCase
) : ViewModel() {
    private val _dummyData = MutableStateFlow("")
    val dummyData = _dummyData.asStateFlow()

    init {
        viewModelScope.launch {
            getAllCurrenciesUseCase().collect {
                Log.d("DemoViewModel", "Currencies: $it")
                _dummyData.value = "All currencies list: ${it.size}"
            }
        }
    }
}