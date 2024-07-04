package com.evg.welcome.data.repository

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository
import com.evg.weather_api.domain.repository.WeatherApiRepository
import com.evg.welcome.domain.mapper.toCity
import com.evg.welcome.domain.model.City
import com.evg.welcome.domain.repository.WelcomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WelcomeRepositoryImpl(
    private val weatherApiRepository: WeatherApiRepository,
    private val databaseRepository: DatabaseRepository,
    private val sharedPrefsRepository: SharedPrefsRepository
): WelcomeRepository {
    override suspend fun getCityList(): Flow<List<City>?> {
        return flow {
            if (!sharedPrefsRepository.getIsCitiesListUnzipped() && weatherApiRepository.isInternetAvailable()) {
                emit(weatherApiRepository.downloadCitiesFile()?.map { it.toCity() })
            } else {
                emit(databaseRepository.getAllCities()?.map { it.toCity() })
            }
        }
    }

    override fun getCityByName(name: String): Flow<City?> {
        return flow { emit(databaseRepository.getCityByName(name = name)?.toCity()) }
    }
}