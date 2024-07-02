package com.evg.database.data.repository

import com.evg.database.data.storage.CityDatabase
import com.evg.database.domain.models.CityDBO
import com.evg.database.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(
    private val cityDatabase: CityDatabase,
): DatabaseRepository {
    // Cities
    override suspend fun getAllCities(): List<CityDBO>? {
        return cityDatabase.cityDao.getAllCities()
    }

    override suspend fun insertCities(cities: List<CityDBO>) {
        cityDatabase.cityDao.insertCities(cities = cities)
    }

    override suspend fun getCityById(id: Int): CityDBO? {
       return cityDatabase.cityDao.getCityById(id = id)
    }

    override suspend fun getCityByName(name: String): CityDBO? {
        return cityDatabase.cityDao.getCityByName(name = name)
    }

}