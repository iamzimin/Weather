package com.evg.weather_city.domain.mapper

import com.evg.weather_api.domain.models.WeeklyForecastCityInfoResponse
import com.evg.weather_api.domain.models.WeeklyForecastCloudsResponse
import com.evg.weather_api.domain.models.WeeklyForecastCoordinatesResponse
import com.evg.weather_api.domain.models.WeeklyForecastMainResponse
import com.evg.weather_api.domain.models.WeeklyForecastResponse
import com.evg.weather_api.domain.models.WeeklyForecastSysResponse
import com.evg.weather_api.domain.models.WeeklyForecastWeatherInfoResponse
import com.evg.weather_api.domain.models.WeeklyForecastWeatherResponse
import com.evg.weather_api.domain.models.WeeklyForecastWindResponse
import com.evg.weather_city.domain.model.WeeklyForecast
import com.evg.weather_city.domain.model.WeeklyForecastCityInfo
import com.evg.weather_city.domain.model.WeeklyForecastClouds
import com.evg.weather_city.domain.model.WeeklyForecastCoordinates
import com.evg.weather_city.domain.model.WeeklyForecastMain
import com.evg.weather_city.domain.model.WeeklyForecastSys
import com.evg.weather_city.domain.model.WeeklyForecastWeather
import com.evg.weather_city.domain.model.WeeklyForecastWeatherInfo
import com.evg.weather_city.domain.model.WeeklyForecastWind

internal fun WeeklyForecastResponse.toWeeklyForecast(): WeeklyForecast {
    return WeeklyForecast(
        cod = cod,
        message = message,
        cnt = cnt,
        weatherList = weatherList.map { it.toWeeklyForecastWeather() },
        city = city.toWeeklyForecastCityInfo()
    )
}

internal fun WeeklyForecastWeatherResponse.toWeeklyForecastWeather(): WeeklyForecastWeather {
    return WeeklyForecastWeather(
        dt = dt,
        main = main.toWeeklyForecastMain(),
        weather = weather.map { it.toWeeklyForecastWeatherInfo() },
        clouds = clouds.toWeeklyForecastClouds(),
        wind = wind.toWeeklyForecastWind(),
        visibility = visibility,
        pop = pop,
        sys = sys.toWeeklyForecastSys(),
        dtTxt = dtTxt
    )
}

internal fun WeeklyForecastMainResponse.toWeeklyForecastMain(): WeeklyForecastMain {
    return WeeklyForecastMain(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        seaLevel = seaLevel,
        groundLevel = groundLevel,
        humidity = humidity,
        tempKf = tempKf
    )
}

internal fun WeeklyForecastWeatherInfoResponse.toWeeklyForecastWeatherInfo(): WeeklyForecastWeatherInfo {
    return WeeklyForecastWeatherInfo(
        id = id,
        main = main,
        description = description,
        icon = icon
    )
}

internal fun WeeklyForecastCloudsResponse.toWeeklyForecastClouds(): WeeklyForecastClouds {
    return WeeklyForecastClouds(
        all = all
    )
}

internal fun WeeklyForecastWindResponse.toWeeklyForecastWind(): WeeklyForecastWind {
    return WeeklyForecastWind(
        speed = speed,
        deg = deg,
        gust = gust
    )
}

internal fun WeeklyForecastSysResponse.toWeeklyForecastSys(): WeeklyForecastSys {
    return WeeklyForecastSys(
        pod = pod
    )
}

internal fun WeeklyForecastCityInfoResponse.toWeeklyForecastCityInfo(): WeeklyForecastCityInfo {
    return WeeklyForecastCityInfo(
        id = id,
        name = name,
        coordinates = coordinates.toWeeklyForecastCoordinates(),
        country = country,
        population = population,
        timezone = timezone,
        sunrise = sunrise,
        sunset = sunset
    )
}

internal fun WeeklyForecastCoordinatesResponse.toWeeklyForecastCoordinates(): WeeklyForecastCoordinates {
    return WeeklyForecastCoordinates(
        lat = lat,
        lon = lon
    )
}