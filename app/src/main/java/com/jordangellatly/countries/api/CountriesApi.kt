package com.jordangellatly.countries.api

import retrofit2.http.GET

interface CountriesApi {

    @GET("countries.json")
    suspend fun getCountries(): List<Country>
}

data class Country(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)

data class Currency(
    val code: String,
    val name: String,
    val symbol: String?
)

data class Language(
    val code: String?,
    val name: String
)
