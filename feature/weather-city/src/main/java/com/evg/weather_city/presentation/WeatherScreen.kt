package com.evg.weather_city.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.evg.weather_city.presentation.mapper.toCurrentWeatherUI
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

    if (currentWeather == null) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    } else {
        currentWeather?.let {
            WeatherContent(
                currentWeather = it
            )
        }
    }

    /*Column(
        modifier = Modifier
            .padding(50.dp)
    ) {
        Text(text = "Opened $cityId")
        currentWeather?.let {
            Text(text = "Update time ${it.getUpdateTime()}")
        }
        *//*Spacer(modifier = Modifier.height(10.dp))
        dailyWeather?.let {
            Text(text = "CurrentWeather $it")
        }*//*
    }*/
}