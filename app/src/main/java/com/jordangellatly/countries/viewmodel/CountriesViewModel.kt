package com.jordangellatly.countries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordangellatly.countries.api.Country
import com.jordangellatly.countries.domain.Result
import com.jordangellatly.countries.domain.asResult
import com.jordangellatly.countries.repository.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CountriesUiState>(CountriesUiState.Loading)
    val uiState: StateFlow<CountriesUiState> = _uiState.asStateFlow()

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            countriesRepository.getCountries().asResult().collect { result ->
                when (result) {
                    Result.Loading -> _uiState.update { CountriesUiState.Loading }
                    is Result.Success -> _uiState.update { CountriesUiState.Success(result.data) }
                    is Result.Error -> _uiState.update { CountriesUiState.Error }
                }
            }
        }
    }
}

sealed interface CountriesUiState {
    data object Loading : CountriesUiState
    data class Success(val countries: List<Country>) : CountriesUiState
    data object Error : CountriesUiState
}
