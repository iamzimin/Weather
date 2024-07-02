package com.evg.welcome.domain.repository

interface WeatherCityRepository {
    suspend fun getCurrentWeather()
    suspend fun getWeatherForDay()
    suspend fun getWeatherForWeekUseCase()
}