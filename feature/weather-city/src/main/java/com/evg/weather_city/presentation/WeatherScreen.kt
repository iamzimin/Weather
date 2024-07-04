package com.evg.weather_city.presentation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.resource.LocalNavHostController
import com.evg.weather_city.presentation.viewmodel.WeatherCityViewModel

@Composable
fun WeatherCityScreen(
    cityId: Int,
    viewModel: WeatherCityViewModel = hiltViewModel<WeatherCityViewModel>(),
) {
    val context = LocalContext.current
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val currentWeather by viewModel.currentWeather.collectAsState()
    val hourlyWeather by viewModel.hourlyForecast.collectAsState()
    val dailyWeather by viewModel.dailyForecast.collectAsState()

    val isCurrentWeatherLoading by viewModel.isCurrentWeatherLoading.collectAsState()
    val isForecastLoading by viewModel.isForecastLoading.collectAsState()

    if (!isInitialized) {
        LaunchedEffect(cityId) {
            viewModel.getCurrentWeather(cityId)
            viewModel.getDailyWeather(cityId)
            viewModel.saveCurrentLocationAsLatest(cityId)
            isInitialized = true
        }
    }

    if (isCurrentWeatherLoading || isForecastLoading) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    } else {
        val current = currentWeather ?: return
        val hourly = hourlyWeather ?: return
        val daily = dailyWeather ?: return

        WeatherContent(
            currentWeather = current,
            hourlyWeather = hourly,
            dailyWeather = daily,
        )
    }

    BackHandler(onBack = {
        val activity = (context as? Activity)
        activity?.finish()
    })
}