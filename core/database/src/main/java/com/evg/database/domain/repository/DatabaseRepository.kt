package com.evg.database.domain.repository

import com.evg.database.domain.models.CityDBO

interface DatabaseRepository {
    //Cities
    suspend fun getAllCities(): List<CityDBO>?
    suspend fun insertCities(cities: List<CityDBO>)
    suspend fun getCityById(id: Int): CityDBO?
    suspend fun getCityByName(name: String): CityDBO?

}