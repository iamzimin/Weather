package com.evg.weather_api.domain.mapper

import com.evg.database.domain.models.WeeklyForecastCityInfoDBO
import com.evg.database.domain.models.WeeklyForecastDBO
import com.evg.database.domain.models.WeeklyForecastMainDBO
import com.evg.database.domain.models.WeeklyForecastWeatherDBO
import com.evg.weather_api.domain.models.WeeklyForecastCityInfoResponse
import com.evg.weather_api.domain.models.WeeklyForecastMainResponse
import com.evg.weather_api.domain.models.WeeklyForecastResponse
import com.evg.weather_api.domain.models.WeeklyForecastWeatherResponse

fun WeeklyForecastResponse.toWeeklyForecastDBO(): WeeklyForecastDBO {
    return WeeklyForecastDBO(
        id = this.city.id,
        weatherList = this.weatherList.map { it.toWeeklyForecastWeatherDBO() },
        city = this.city.toWeeklyForecastCityInfoDBO()
    )
}

fun WeeklyForecastWeatherResponse.toWeeklyForecastWeatherDBO(): WeeklyForecastWeatherDBO {
    val weatherInfo = this.weather.first()
    return WeeklyForecastWeatherDBO(
        dt = this.dt,
        main = this.main.toWeeklyForecastMainDBO(),
        weatherId = weatherInfo.id,
        weatherMain = weatherInfo.main,
        weatherIcon = weatherInfo.icon
    )
}

fun WeeklyForecastMainResponse.toWeeklyForecastMainDBO(): WeeklyForecastMainDBO {
    return WeeklyForecastMainDBO(
        temp = this.temp,
        tempMin = this.tempMin,
        tempMax = this.tempMax
    )
}

fun WeeklyForecastCityInfoResponse.toWeeklyForecastCityInfoDBO(): WeeklyForecastCityInfoDBO {
    return WeeklyForecastCityInfoDBO(
        name = this.name,
        timezone = this.timezone
    )
}