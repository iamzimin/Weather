package com.evg.weather_city.domain.mapper

import com.evg.database.domain.models.WeeklyForecastCityInfoDBO
import com.evg.database.domain.models.WeeklyForecastDBO
import com.evg.database.domain.models.WeeklyForecastMainDBO
import com.evg.database.domain.models.WeeklyForecastWeatherDBO
import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.model.WeeklyForecastCityInfo
import com.evg.weather_city.domain.model.WeeklyForecastMain
import com.evg.weather_city.domain.model.WeeklyForecastWeather

internal fun WeeklyForecastDBO.toWeeklyForecast(): WeeklyForecast {
    return WeeklyForecast(
        weatherList = weatherList.map { it.toWeeklyForecastWeather() },
        city = city.toWeeklyForecastCityInfo(id = id)
    )
}

internal fun WeeklyForecastWeatherDBO.toWeeklyForecastWeather(): WeeklyForecastWeather {
    return WeeklyForecastWeather(
        dt = dt,
        main = main.toWeeklyForecastMain(),
        weatherId = weatherId,
        weatherMain = weatherMain,
        weatherIcon = weatherIcon
    )
}

internal fun WeeklyForecastMainDBO.toWeeklyForecastMain(): WeeklyForecastMain {
    return WeeklyForecastMain(
        temp = temp,
        tempMin = tempMin,
        tempMax = tempMax
    )
}

internal fun WeeklyForecastCityInfoDBO.toWeeklyForecastCityInfo(id: Int): WeeklyForecastCityInfo {
    return WeeklyForecastCityInfo(
        id = id,
        name = name,
        timezone = timezone
    )
}
