package com.evg.weather_city.domain.usecase

import com.evg.welcome.domain.repository.WeatherCityRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherCityRepository: WeatherCityRepository
) {
    fun invoke() {
        //return weatherCityRepository
    }
}