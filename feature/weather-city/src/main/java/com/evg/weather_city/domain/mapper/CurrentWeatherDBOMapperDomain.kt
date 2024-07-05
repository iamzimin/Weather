package com.evg.weather_city.domain.mapper

import com.evg.database.domain.models.CurrentWeatherCloudsDBO
import com.evg.database.domain.models.CurrentWeatherDBO
import com.evg.database.domain.models.CurrentWeatherMainDBO
import com.evg.database.domain.models.CurrentWeatherSysDBO
import com.evg.database.domain.models.CurrentWeatherWindDBO
import com.evg.weather_city.domain.model.CurrentWeather
import com.evg.weather_city.domain.model.CurrentWeatherClouds
import com.evg.weather_city.domain.model.CurrentWeatherMain
import com.evg.weather_city.domain.model.CurrentWeatherSys
import com.evg.weather_city.domain.model.CurrentWeatherWind

internal fun CurrentWeatherDBO.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        weatherId = weatherId,
        weatherMain = weatherMain,
        weatherDescription = weatherDescription,
        weatherIcon = weatherIcon,
        main = main.toCurrentWeatherMain(),
        visibility = visibility,
        wind = wind.toCurrentWeatherWind(),
        clouds = clouds.toCurrentWeatherClouds(),
        timestamp = timestamp,
        sys = sys.toCurrentWeatherSys(),
        timezone = timezone,
        id = id,
        name = name
    )
}

internal fun CurrentWeatherMainDBO.toCurrentWeatherMain(): CurrentWeatherMain {
    return CurrentWeatherMain(
        temp = temp,
        feelsLike = feelsLike,
        tempMin = tempMin,
        tempMax = tempMax,
        pressure = pressure,
        humidity = humidity,
        seaLevel = seaLevel,
        groundLevel = groundLevel
    )
}

internal fun CurrentWeatherWindDBO.toCurrentWeatherWind(): CurrentWeatherWind {
    return CurrentWeatherWind(
        speed = speed,
        deg = deg,
        gust = gust
    )
}

internal fun CurrentWeatherCloudsDBO.toCurrentWeatherClouds(): CurrentWeatherClouds {
    return CurrentWeatherClouds(
        cloudiness = cloudiness
    )
}

internal fun CurrentWeatherSysDBO.toCurrentWeatherSys(): CurrentWeatherSys {
    return CurrentWeatherSys(
        country = country,
        sunrise = sunrise,
        sunset = sunset
    )
}
