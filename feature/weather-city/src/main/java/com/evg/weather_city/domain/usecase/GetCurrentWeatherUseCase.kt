package com.evg.weather_city.domain.usecase

import com.evg.weather_city.domain.model.CurrentWeather
import com.evg.weather_city.domain.repository.WeatherCityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherCityRepository: WeatherCityRepository
) {
    fun invoke(cityId: Int): Flow<CurrentWeather?> {
        return weatherCityRepository.getCurrentWeather(cityId = cityId)
    }
}