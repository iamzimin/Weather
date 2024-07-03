package com.evg.weather_api.domain.repository

import com.evg.weather_api.domain.models.CityResponse
import com.evg.weather_api.domain.models.CurrentWeatherResponse
import com.evg.weather_api.domain.models.WeeklyForecastResponse

interface WeatherApiRepository {
    suspend fun downloadCitiesFile(): List<CityResponse>?
    suspend fun getCurrentWeather(cityId: Int): CurrentWeatherResponse?
    suspend fun getForecastWeeklyWeather(cityId: Int): WeeklyForecastResponse?
}