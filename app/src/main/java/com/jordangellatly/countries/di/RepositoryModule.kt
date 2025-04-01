package com.jordangellatly.countries.di

import com.jordangellatly.countries.repository.CountriesRepository
import com.jordangellatly.countries.repository.CountriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCountriesRepository(countriesRepository: CountriesRepositoryImpl): CountriesRepository
}
