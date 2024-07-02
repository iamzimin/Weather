package com.evg.weather_city.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.weather_city.presentation.viewmodel.WeatherCityViewModel

@Composable
fun WeatherCityScreen(
    cityId: Int,
    viewModel: WeatherCityViewModel = hiltViewModel<WeatherCityViewModel>(),
) {
    Column {
        Text(text = "Opened $cityId")
    }
}