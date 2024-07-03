package com.evg.selection_city.data.repository

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.selection_city.domain.mapper.toCity
import com.evg.selection_city.domain.mapper.toCityInfo
import com.evg.selection_city.domain.model.City
import com.evg.selection_city.domain.model.CityInfo
import com.evg.selection_city.domain.repository.SelectionCityRepository
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SelectionCityRepositoryImpl(
    private val databaseRepository: DatabaseRepository,
    private val sharedPrefsRepository: SharedPrefsRepository,
): SelectionCityRepository {
    override fun getCitiesList(): Flow<List<City>?> {
        return flow { emit(databaseRepository.getAllCities()?.map { it.toCity() }) }
    }

    override fun getCityByName(name: String): Flow<City?> {
        return flow { emit(databaseRepository.getCityByName(name = name)?.toCity()) }
    }

    override suspend fun deleteCityById(id: Int) {
        databaseRepository.deleteCurrentWeatherById(id = id)
        databaseRepository.deleteWeeklyForecastById(id = id)
    }

    override fun getMyCitesList(): Flow<List<CityInfo>?> {
        return flow { emit(databaseRepository.getAllCurrentWeathers()?.map { it.toCityInfo() }) }
    }

    override fun getLatestCity(): Int? {
        return sharedPrefsRepository.getLatestCity()
    }
}