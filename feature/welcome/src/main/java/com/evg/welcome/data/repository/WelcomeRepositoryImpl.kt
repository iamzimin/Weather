package com.evg.welcome.data.repository

import android.content.Context
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.weather_api.domain.repository.WeatherApiRepository
import com.evg.welcome.domain.mapper.toCity
import com.evg.welcome.domain.model.City
import com.evg.welcome.domain.repository.WelcomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class WelcomeRepositoryImpl(
    private val context: Context,
    private val weatherApiRepository: WeatherApiRepository,
    private val databaseRepository: DatabaseRepository,
): WelcomeRepository {
    override suspend fun getCityList(): Flow<List<City>?> {
        val sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val isCityDownloaded = sharedPreferences.getBoolean("isCityDownloaded", false)

        return flow {
            if (!isCityDownloaded) {
                emit(weatherApiRepository.downloadCityFile()?.map { it.toCity() })
            } else {
                emit(databaseRepository.getAllCities()?.map { it.toCity() })
            }
        }
    }

    override fun getCityByName(name: String): Flow<City?> {
        return flow { emit(databaseRepository.getCityByName(name = name)?.toCity()) }
    }
}