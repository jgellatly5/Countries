package com.jordangellatly.countries.repository

import com.jordangellatly.countries.api.CountriesApi
import com.jordangellatly.countries.api.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CountriesRepository {
    fun getCountries(): Flow<List<Country>>
}

class CountriesRepositoryImpl @Inject constructor(
    private val countriesApi: CountriesApi
) : CountriesRepository {

    override fun getCountries(): Flow<List<Country>> =
        flow {
            emit(countriesApi.getCountries())
        }
}
