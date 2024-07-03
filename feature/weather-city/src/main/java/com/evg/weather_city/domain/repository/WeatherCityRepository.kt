package com.evg.weather_city.domain.repository

import com.evg.weather_city.domain.model.CurrentWeather
import com.evg.weather_city.domain.model.WeeklyForecast
import kotlinx.coroutines.flow.Flow

interface WeatherCityRepository {
    fun getCurrentWeather(cityId: Int): Flow<CurrentWeather?>
    fun getWeatherForWeekUseCase(cityId: Int): Flow<WeeklyForecast?>
    fun saveLatestCity(id: Int)
}