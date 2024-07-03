package com.evg.weather_city.presentation.mapper

import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.utils.kelvinToCelsius
import com.evg.weather_city.presentation.model.HourlyForecastUI

fun WeeklyForecast.toHourlyForecastUI(size: Int): List<HourlyForecastUI> {
    return weatherList.take(size).map { weather ->
        HourlyForecastUI(
            timestamp = weather.dt,
            temp = weather.main.temp.kelvinToCelsius(),
            icon = weather.weatherIcon
        )
    }
}
