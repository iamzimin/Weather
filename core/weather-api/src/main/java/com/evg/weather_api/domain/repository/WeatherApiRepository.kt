package com.evg.weather_api.domain.repository

import com.evg.weather_api.domain.models.CityResponse
import okhttp3.ResponseBody
import retrofit2.Response

interface WeatherApiRepository {
    suspend fun downloadCityFile(): List<CityResponse>?
}