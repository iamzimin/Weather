package com.evg.weather_api.domain.service

import com.evg.weather_api.domain.models.CurrentWeatherResponse
import com.evg.weather_api.domain.models.WeeklyForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("id") cityId: Int,
        @Query("appid") apiKey: String
    ): CurrentWeatherResponse?

    @GET("forecast")
    suspend fun getWeeklyForecast(
        @Query("id") cityId: Int,
        @Query("appid") apiKey: String
    ): WeeklyForecastResponse?
}