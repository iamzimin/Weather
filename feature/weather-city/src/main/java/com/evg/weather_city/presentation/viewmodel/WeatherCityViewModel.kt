package com.evg.weather_city.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.usecase.WeatherCityUseCases
import com.evg.weather_city.presentation.mapper.toCurrentWeatherUI
import com.evg.weather_city.presentation.mapper.toDailyForecastUI
import com.evg.weather_city.presentation.mapper.toHourlyForecastUI
import com.evg.weather_city.presentation.model.CurrentWeatherUI
import com.evg.weather_city.presentation.model.DailyForecastUI
import com.evg.weather_city.presentation.model.HourlyForecastUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherCityViewModel @Inject constructor(
    private val weatherCityUseCases: WeatherCityUseCases,
): ViewModel() {
    private val _currentWeather = MutableStateFlow<CurrentWeatherUI?>(null)
    val currentWeather: StateFlow<CurrentWeatherUI?> get() = _currentWeather

    private val _hourlyForecast = MutableStateFlow<List<HourlyForecastUI>?>(null)
    val hourlyForecast: StateFlow<List<HourlyForecastUI>?> get() = _hourlyForecast

    private val _dailyForecast = MutableStateFlow<List<DailyForecastUI>?>(null)
    val dailyForecast: StateFlow<List<DailyForecastUI>?> get() = _dailyForecast

    fun getCurrentWeather(cityId: Int) {
        viewModelScope.launch {
            //_isCurrentWeatherLoading.value = true
            weatherCityUseCases.getCurrentWeatherUseCase.invoke(cityId = cityId)
                .collect { weather ->
                    _currentWeather.value = weather?.toCurrentWeatherUI()
                    //_isCurrentWeatherLoading.value = false
                }
        }
    }

    fun getDailyWeather(cityId: Int) {
        viewModelScope.launch {
            //_isForecastLoading.value = true
            weatherCityUseCases.getWeatherForWeekUseCase.invoke(cityId = cityId)
                .collect { weather ->
                    _hourlyForecast.value = weather?.toHourlyForecastUI(size = 9)
                    _dailyForecast.value = weather?.toDailyForecastUI()
                    //_isForecastLoading.value = false
                }
        }
    }

    fun saveCurrentLocationAsLatest(id: Int) {
        weatherCityUseCases.saveLatestCityUseCase.invoke(id = id)
    }
}