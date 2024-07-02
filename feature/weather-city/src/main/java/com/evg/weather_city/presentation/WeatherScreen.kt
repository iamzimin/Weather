package com.evg.weather_city.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.weather_city.presentation.viewmodel.WeatherCityViewModel

@Composable
fun WeatherCityScreen(
    cityId: Int,
    viewModel: WeatherCityViewModel = hiltViewModel<WeatherCityViewModel>(),
) {
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val currentWeather by viewModel.currentWeather.collectAsState()
    val dailyWeather by viewModel.dailyForecast.collectAsState()

    if (!isInitialized) {
        LaunchedEffect(cityId) {
            viewModel.getCurrentWeather(cityId)
            viewModel.getDailyWeather(cityId)
            isInitialized = true
        }
    }

    Column(
        modifier = Modifier
            .padding(50.dp)
    ) {
        Text(text = "Opened $cityId")
        currentWeather?.let {
            Text(text = "CurrentWeather ${it.base}")
        }
        dailyWeather?.let {
            Text(text = "CurrentWeather ${it.cod}")
        }
    }
}