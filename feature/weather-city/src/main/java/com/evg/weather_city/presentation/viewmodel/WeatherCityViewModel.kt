package com.evg.weather_city.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evg.weather_city.domain.model.CurrentWeather
import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.usecase.WeatherCityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherCityViewModel @Inject constructor(
    private val weatherCityUseCases: WeatherCityUseCases,
): ViewModel() {
    private val _currentWeather = MutableStateFlow<CurrentWeather?>(null)
    val currentWeather: StateFlow<CurrentWeather?> get() = _currentWeather

    private val _dailyForecast = MutableStateFlow<WeeklyForecast?>(null)
    val dailyForecast: StateFlow<WeeklyForecast?> get() = _dailyForecast

    private val _weeklyForecast = MutableStateFlow<WeeklyForecast?>(null)
    val weeklyForecast: StateFlow<WeeklyForecast?> get() = _weeklyForecast

    fun getCurrentWeather(cityId: Int) {
        viewModelScope.launch {
            //_isCurrentWeatherLoading.value = true
            weatherCityUseCases.getCurrentWeatherUseCase.invoke(cityId = cityId)
                .collect { weather ->
                    _currentWeather.value = weather
                    //_isCurrentWeatherLoading.value = false
                }
        }
    }

    fun getDailyWeather(cityId: Int) {
        viewModelScope.launch {
            //_isDailyWeatherLoading.value = true
            weatherCityUseCases.getWeatherForDayUseCase.invoke(cityId = cityId)
                .collect { weather ->
                    _dailyForecast.value = weather
                    //_isDailyWeatherLoading.value = false
                }
        }
    }
}