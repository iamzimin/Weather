package com.evg.database.data.repository

import com.evg.database.data.storage.CityDatabase
import com.evg.database.data.storage.CurrentWeatherDatabase
import com.evg.database.data.storage.WeeklyForecastDatabase
import com.evg.database.domain.models.CityDBO
import com.evg.database.domain.models.CurrentWeatherDBO
import com.evg.database.domain.models.WeeklyForecastDBO
import com.evg.database.domain.repository.DatabaseRepository

class DatabaseRepositoryImpl(
    private val cityDatabase: CityDatabase,
    private val currentWeatherDatabase: CurrentWeatherDatabase,
    private val weeklyForecastDatabase: WeeklyForecastDatabase
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

    // Current Weather
    override suspend fun getAllCurrentWeathers(): List<CurrentWeatherDBO>? {
        return currentWeatherDatabase.currentWeatherDao.getAllCurrentWeathers()
    }

    override suspend fun insertCurrentWeather(currentWeather: CurrentWeatherDBO) {
        currentWeatherDatabase.currentWeatherDao.insertCurrentWeather(currentWeather = currentWeather)
    }

    override suspend fun getCurrentWeatherById(id: Int): CurrentWeatherDBO? {
        return currentWeatherDatabase.currentWeatherDao.getCurrentWeatherById(id = id)
    }

    override suspend fun deleteCurrentWeatherById(id: Int) {
        currentWeatherDatabase.currentWeatherDao.deleteCurrentWeatherById(id = id)
    }

    // Weekly Forecast
    override suspend fun insertWeeklyForecast(weeklyForecast: WeeklyForecastDBO) {
        weeklyForecastDatabase.weeklyForecastDao.insertWeeklyForecast(weeklyForecast = weeklyForecast)
    }

    override suspend fun getWeeklyForecastById(id: Int): WeeklyForecastDBO? {
        return weeklyForecastDatabase.weeklyForecastDao.getWeeklyForecastById(id = id)
    }

    override suspend fun deleteWeeklyForecastById(id: Int) {
        weeklyForecastDatabase.weeklyForecastDao.deleteWeeklyForecastById(id = id)
    }
}