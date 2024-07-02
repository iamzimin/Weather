package com.evg.weather_city.domain.usecase

import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.repository.WeatherCityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
class GetWeatherForDayUseCase @Inject constructor(
    private val weatherCityRepository: WeatherCityRepository
) {
    fun invoke(cityId: Int): Flow<WeeklyForecast?> {
        return weatherCityRepository.getWeatherForWeekUseCase(cityId = cityId)
    }
}*/
