package com.evg.weather_city.presentation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.weather_city.presentation.viewmodel.WeatherCityViewModel

@Composable
fun WeatherCityScreen(
    cityId: Int,
    viewModel: WeatherCityViewModel = hiltViewModel<WeatherCityViewModel>(),
) {
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val currentWeather by viewModel.currentWeather.collectAsState()
    val hourlyWeather by viewModel.hourlyForecast.collectAsState()
    val dailyWeather by viewModel.dailyForecast.collectAsState()

    if (!isInitialized) {
        LaunchedEffect(cityId) {
            viewModel.getCurrentWeather(cityId)
            viewModel.getDailyWeather(cityId)
            isInitialized = true
        }
    }

    if (currentWeather == null || hourlyWeather == null || dailyWeather == null) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    } else {
        WeatherContent(
            currentWeather = currentWeather!!,
            hourlyWeather = hourlyWeather!!,
            dailyWeather = dailyWeather!!,
        )
    }
}