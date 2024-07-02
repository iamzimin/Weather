package com.evg.weather_city.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.evg.weather_city.domain.usecase.WeatherCityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherCityViewModel @Inject constructor(
    private val weatherCityUseCases: WeatherCityUseCases,
): ViewModel() {

}