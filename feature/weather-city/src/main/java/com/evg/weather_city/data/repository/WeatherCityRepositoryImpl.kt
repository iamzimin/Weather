package com.evg.weather_city.data.repository

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository
import com.evg.weather_api.domain.repository.WeatherApiRepository
import com.evg.weather_city.domain.mapper.toCurrentWeather
import com.evg.weather_city.domain.mapper.toWeeklyForecast
import com.evg.weather_city.domain.model.CurrentWeather
import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.repository.WeatherCityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherCityRepositoryImpl(
    private val apiRepository: WeatherApiRepository,
    private val databaseRepository: DatabaseRepository,
    private val sharedPrefsRepository: SharedPrefsRepository,
): WeatherCityRepository {
    override fun getCurrentWeather(cityId: Int): Flow<CurrentWeather?> {
        return if (apiRepository.isInternetAvailable()) {
            flow { emit(apiRepository.getCurrentWeather(cityId = cityId)?.toCurrentWeather()) }
        } else {
            flow { emit(databaseRepository.getCurrentWeatherById(id = cityId)?.toCurrentWeather()) }
        }
    }

    override fun getWeatherForWeekUseCase(cityId: Int): Flow<WeeklyForecast?> {
        return if (apiRepository.isInternetAvailable()) {
            flow { emit(apiRepository.getForecastWeeklyWeather(cityId = cityId)?.toWeeklyForecast()) }
        } else {
            flow { emit(databaseRepository.getWeeklyForecastById(id = cityId)?.toWeeklyForecast()) }
        }
    }

    override fun saveLatestCity(id: Int) {
        sharedPrefsRepository.saveLatestCity(id = id)
    }
}