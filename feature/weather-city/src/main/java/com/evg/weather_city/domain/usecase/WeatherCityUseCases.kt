package com.evg.weather_city.domain.usecase

import javax.inject.Inject

data class WeatherCityUseCases @Inject constructor(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val getWeatherForWeekUseCase: GetWeatherForWeekUseCase,
)