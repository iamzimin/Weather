package com.evg.database.domain.repository

import com.evg.database.domain.models.CityDBO
import com.evg.database.domain.models.CurrentWeatherDBO
import com.evg.database.domain.models.WeeklyForecastDBO

interface DatabaseRepository {
    // Cities
    suspend fun getAllCities(): List<CityDBO>?
    suspend fun insertCities(cities: List<CityDBO>)
    suspend fun getCityById(id: Int): CityDBO?
    suspend fun getCityByName(name: String): CityDBO?

    // Current Weather
    suspend fun getAllCurrentWeathers(): List<CurrentWeatherDBO>?
    suspend fun insertCurrentWeather(currentWeather: CurrentWeatherDBO)
    suspend fun getCurrentWeatherById(id: Int): CurrentWeatherDBO?
    suspend fun deleteCurrentWeatherById(id: Int)

    // Weekly Forecast
    suspend fun insertWeeklyForecast(weeklyForecast: WeeklyForecastDBO)
    suspend fun getWeeklyForecastById(id: Int): WeeklyForecastDBO?
    suspend fun deleteWeeklyForecastById(id: Int)
}