package com.evg.weather_city.presentation.mapper

import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.model.WeeklyForecastWeather
import com.evg.weather_city.domain.utils.isStartOfNewDay
import com.evg.weather_city.domain.utils.kelvinToCelsius
import com.evg.weather_city.presentation.model.DailyForecastUI
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

internal fun WeeklyForecast.toDailyForecastUI(): List<DailyForecastUI> {
    val dailyForecasts = mutableListOf<DailyForecastUI>()
    var currentDayForecasts = mutableListOf<WeeklyForecastWeather>()

    weatherList.forEach { forecast ->
        if (currentDayForecasts.isNotEmpty() && isStartOfNewDay(currentDayForecasts.last().dt, forecast.dt, this.city.timezone)) {
            val dailyForecast = mapToDailyForecast(currentDayForecasts)
            dailyForecasts.add(dailyForecast)
            currentDayForecasts = mutableListOf()
        }
        currentDayForecasts.add(forecast)
    }

    if (currentDayForecasts.isNotEmpty()) { //TODO
        val dailyForecast = mapToDailyForecast(currentDayForecasts)
        dailyForecasts.add(dailyForecast)
    }

    return dailyForecasts
}

private fun mapToDailyForecast(forecasts: List<WeeklyForecastWeather>): DailyForecastUI {
    val timestamp = forecasts.firstOrNull()?.dt ?: 0L

    val tempMin = forecasts.minOfOrNull { it.main.tempMin }?.kelvinToCelsius() ?: 0
    val tempMax = forecasts.maxOfOrNull { it.main.tempMax }?.kelvinToCelsius() ?: 0

    val idCount = mutableMapOf<Int, Int>()

    forecasts.forEach { forecast ->
        val idKey = forecast.weatherId
        idCount[idKey] = (idCount[idKey] ?: 0) + 1
    }

    val mostFrequentId = idCount.maxByOrNull { it.value }?.value ?: -1
    val mostFrequentIcon = forecasts.getOrNull(mostFrequentId)?.weatherIcon ?: ""
    val mostFrequentMain = forecasts.getOrNull(mostFrequentId)?.weatherMain?: ""

    return DailyForecastUI(
        timestamp = timestamp,
        icon = mostFrequentIcon,
        weatherDescription = mostFrequentMain,
        tempMin = tempMin,
        tempMax = tempMax
    )
}