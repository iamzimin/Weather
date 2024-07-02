package com.evg.weather_city.data.repository

import com.evg.weather_api.domain.repository.WeatherApiRepository
import com.evg.weather_city.domain.mapper.toCurrentWeather
import com.evg.weather_city.domain.mapper.toWeeklyForecast
import com.evg.weather_city.domain.model.CurrentWeather
import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.repository.WeatherCityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherCityRepositoryImpl(
    private val apiRepository: WeatherApiRepository
): WeatherCityRepository {
    override fun getCurrentWeather(cityId: Int): Flow<CurrentWeather?> {
        return flow {
            emit(apiRepository.getCurrentWeather(cityId = cityId)?.toCurrentWeather())
        }
        //return apiRepository.getCurrentWeather(cityId = cityId)?.toCurrentWeather()
    }

    override fun getWeatherForDay(cityId: Int): Flow<WeeklyForecast?> {
        return flow {
            emit(apiRepository.getForecastWeeklyWeather(cityId = cityId)?.toWeeklyForecast())
        }
        //return apiRepository.getForecastWeeklyWeather(cityId = cityId)?.toWeeklyForecast()
    }

    override fun getWeatherForWeekUseCase(cityId: Int): Flow<WeeklyForecast?> {
        return flow {
            emit(apiRepository.getForecastWeeklyWeather(cityId = cityId)?.toWeeklyForecast())
        }
        //return apiRepository.getForecastWeeklyWeather(cityId = cityId)?.toWeeklyForecast()
    }
}