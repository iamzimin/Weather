package com.evg.selection_city.domain.repository

import com.evg.selection_city.domain.model.City
import com.evg.selection_city.domain.model.CityInfo
import kotlinx.coroutines.flow.Flow

interface SelectionCityRepository {
    fun getCitiesList(): Flow<List<City>?>
    fun getCityByName(name: String): Flow<City?>
    suspend fun deleteCityById(id: Int)
    fun getMyCitesList(): Flow<List<CityInfo>?>
}