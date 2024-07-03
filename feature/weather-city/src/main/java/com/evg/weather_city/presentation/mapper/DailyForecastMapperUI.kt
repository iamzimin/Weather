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

    val iconCount = mutableMapOf<String, Int>()
    val mainCount = mutableMapOf<String, Int>()

    forecasts.forEach { forecast ->
        val iconKey = forecast.weatherIcon.takeWhile { it.isDigit() }
        iconCount[iconKey] = (iconCount[iconKey] ?: 0) + 1

        val mainKey = forecast.weatherMain
        mainCount[mainKey] = (mainCount[mainKey] ?: 0) + 1
    }

    val mostFrequentIconDigits = iconCount.maxByOrNull { it.value }?.key
    val mostFrequentIcon = if (mostFrequentIconDigits == null) "" else "${mostFrequentIconDigits}d"
    val mostFrequentMain = mainCount.maxByOrNull { it.value }?.key ?: ""

    return DailyForecastUI(
        timestamp = timestamp,
        icon = mostFrequentIcon,
        weatherDescription = mostFrequentMain,
        tempMin = tempMin,
        tempMax = tempMax
    )
}