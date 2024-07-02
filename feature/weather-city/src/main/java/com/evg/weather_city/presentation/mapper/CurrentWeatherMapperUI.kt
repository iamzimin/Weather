package com.evg.weather_city.presentation.mapper

import com.evg.weather_city.domain.model.CurrentWeather
import com.evg.weather_city.domain.model.CurrentWeatherClouds
import com.evg.weather_city.domain.model.CurrentWeatherCoordinates
import com.evg.weather_city.domain.model.CurrentWeatherInfo
import com.evg.weather_city.domain.model.CurrentWeatherMain
import com.evg.weather_city.domain.model.CurrentWeatherRain
import com.evg.weather_city.domain.model.CurrentWeatherSnow
import com.evg.weather_city.domain.model.CurrentWeatherSys
import com.evg.weather_city.domain.model.CurrentWeatherWind
import com.evg.weather_city.presentation.model.CurrentWeatherCloudsUI
import com.evg.weather_city.presentation.model.CurrentWeatherCoordinatesUI
import com.evg.weather_city.presentation.model.CurrentWeatherInfoUI
import com.evg.weather_city.presentation.model.CurrentWeatherMainUI
import com.evg.weather_city.presentation.model.CurrentWeatherRainUI
import com.evg.weather_city.presentation.model.CurrentWeatherSnowUI
import com.evg.weather_city.presentation.model.CurrentWeatherSysUI
import com.evg.weather_city.presentation.model.CurrentWeatherUI
import com.evg.weather_city.presentation.model.CurrentWeatherWindUI

internal fun CurrentWeather.toCurrentWeatherUI(): CurrentWeatherUI {
    return CurrentWeatherUI(
        coordinates = coordinates.toCurrentWeatherCoordinatesUI(),
        weather = weather.map { it.toCurrentWeatherInfoUI() },
        main = main.toCurrentWeatherMainUI(),
        visibility = visibility,
        wind = wind.toCurrentWeatherWindUI(),
        rain = rain?.toCurrentWeatherRainUI(),
        snow = snow?.toCurrentWeatherSnowUI(),
        clouds = clouds.toCurrentWeatherCloudsUI(),
        timestamp = timestamp,
        sys = sys.toCurrentWeatherSysUI(),
        timezone = timezone,
        id = id,
        name = name
    )
}

internal fun CurrentWeatherCoordinates.toCurrentWeatherCoordinatesUI(): CurrentWeatherCoordinatesUI {
    return CurrentWeatherCoordinatesUI(
        lon = lon,
        lat = lat
    )
}

internal fun CurrentWeatherInfo.toCurrentWeatherInfoUI(): CurrentWeatherInfoUI {
    return CurrentWeatherInfoUI(
        id = id,
        main = main,
        description = description,
        icon = icon
    )
}

internal fun CurrentWeatherMain.toCurrentWeatherMainUI(): CurrentWeatherMainUI {
    return CurrentWeatherMainUI(
        temp = temp.kelvinToCelsius(),
        feelsLike = feelsLike.kelvinToCelsius(),
        tempMin = tempMin.kelvinToCelsius(),
        tempMax = tempMax.kelvinToCelsius(),
        pressure = pressure,
        humidity = humidity,
        seaLevel = seaLevel,
        groundLevel = groundLevel
    )
}

internal fun CurrentWeatherWind.toCurrentWeatherWindUI(): CurrentWeatherWindUI {
    return CurrentWeatherWindUI(
        speed = speed,
        deg = deg,
        gust = gust
    )
}

internal fun CurrentWeatherRain.toCurrentWeatherRainUI(): CurrentWeatherRainUI {
    return CurrentWeatherRainUI(
        oneHour = oneHour,
        threeHour = threeHour
    )
}

internal fun CurrentWeatherSnow.toCurrentWeatherSnowUI(): CurrentWeatherSnowUI {
    return CurrentWeatherSnowUI(
        oneHour = oneHour,
        threeHour = threeHour
    )
}

internal fun CurrentWeatherClouds.toCurrentWeatherCloudsUI(): CurrentWeatherCloudsUI {
    return CurrentWeatherCloudsUI(
        cloudiness = cloudiness
    )
}

internal fun CurrentWeatherSys.toCurrentWeatherSysUI(): CurrentWeatherSysUI {
    return CurrentWeatherSysUI(
        country = country,
        sunrise = sunrise,
        sunset = sunset
    )
}