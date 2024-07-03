package com.evg.weather_city.domain.usecase

import com.evg.weather_city.domain.repository.WeatherCityRepository
import javax.inject.Inject

class SaveLatestCityUseCase @Inject constructor(
    private val weatherCityRepository: WeatherCityRepository
) {
    fun invoke(id: Int) {
        return weatherCityRepository.saveLatestCity(id = id)
    }
}