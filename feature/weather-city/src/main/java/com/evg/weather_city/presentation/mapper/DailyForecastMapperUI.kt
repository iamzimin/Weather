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

    val weatherIdCounts = mutableMapOf<Int, Int>()

    forecasts.forEach { forecast ->
        val count = weatherIdCounts.getOrDefault(forecast.weatherId, 0)
        weatherIdCounts[forecast.weatherId] = count + 1
    }

    val mostFrequentWeatherId = weatherIdCounts.maxByOrNull { it.value }?.key
    val index = forecasts.indexOfFirst { it.weatherId == mostFrequentWeatherId }

    val mostFrequentIconWithDayTime = forecasts.getOrNull(index)?.weatherIcon ?: ""
    val mostFrequentIcon = mostFrequentIconWithDayTime.takeWhile { it.isDigit() }.plus("d")
    val mostFrequentMain = forecasts.getOrNull(index)?.weatherMain?: ""

    return DailyForecastUI(
        timestamp = timestamp,
        icon = mostFrequentIcon,
        weatherDescription = mostFrequentMain,
        tempMin = tempMin,
        tempMax = tempMax
    )
}