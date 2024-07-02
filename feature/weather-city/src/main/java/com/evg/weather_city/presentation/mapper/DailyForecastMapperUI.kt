package com.evg.weather_city.presentation.mapper

import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.model.WeeklyForecastWeather
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

private fun isStartOfNewDay(previousDt: Long, currentDt: Long, timezoneOffsetSeconds: Int): Boolean {
    val previousDay = ZonedDateTime.ofInstant(Instant.ofEpochSecond(previousDt), ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds)).toLocalDate()
    val currentDay = ZonedDateTime.ofInstant(Instant.ofEpochSecond(currentDt), ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds)).toLocalDate()
    return previousDay != currentDay
}

private fun mapToDailyForecast(forecasts: List<WeeklyForecastWeather>): DailyForecastUI {
    val timestamp = forecasts.firstOrNull()?.dt ?: 0L

    val tempMin = forecasts.minOfOrNull { it.main.tempMin }?.kelvinToCelsius() ?: 0
    val tempMax = forecasts.maxOfOrNull { it.main.tempMax }?.kelvinToCelsius() ?: 0

    val iconCount = mutableMapOf<String, Int>()
    val mainCount = mutableMapOf<String, Int>()

    forecasts.forEach { forecast ->
        forecast.weather.forEach { weatherInfo ->
            val iconKey = weatherInfo.icon.takeWhile { it.isDigit() }
            iconCount[iconKey] = (iconCount[iconKey] ?: 0) + 1

            val mainKey = weatherInfo.main
            mainCount[mainKey] = (mainCount[mainKey] ?: 0) + 1
        }
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