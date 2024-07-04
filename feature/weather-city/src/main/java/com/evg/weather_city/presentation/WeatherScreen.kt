package com.evg.weather_city.presentation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.resource.CustomSwipeRefreshIndicator
import com.evg.weather_city.presentation.viewmodel.WeatherCityViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

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

    val refreshingState = rememberSwipeRefreshState(
        isRefreshing = false
    )

    if (!isInitialized) {
        LaunchedEffect(cityId) {
            viewModel.getCurrentWeather(cityId)
            viewModel.getDailyWeather(cityId)
            viewModel.saveCurrentLocationAsLatest(cityId)
            isInitialized = true
        }
    }

    SwipeRefresh(
        state = refreshingState,
        onRefresh = {
            viewModel.getCurrentWeather(cityId)
            viewModel.getDailyWeather(cityId)
        },
        indicator = { state, trigger ->
            CustomSwipeRefreshIndicator(
                state = state,
                trigger = trigger,
            )
        },
    ) {
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
            if (currentWeather != null && hourlyWeather != null && dailyWeather != null) {
                WeatherContent(
                    currentWeather = currentWeather!!,
                    hourlyWeather = hourlyWeather!!,
                    dailyWeather = dailyWeather!!,
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "City loading error. Swipe to refresh",
                        textAlign = TextAlign.Center,
                    )
                }
                Toast.makeText(context, "City loading error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    BackHandler(onBack = {
        val activity = (context as? Activity)
        activity?.finish()
    })
}