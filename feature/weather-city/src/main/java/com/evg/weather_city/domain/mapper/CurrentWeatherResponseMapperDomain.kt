package com.evg.weather_city.domain.mapper

import com.evg.weather_api.domain.models.CurrentWeatherCloudsResponse
import com.evg.weather_api.domain.models.CurrentWeatherInfoResponse
import com.evg.weather_api.domain.models.CurrentWeatherMainResponse
import com.evg.weather_api.domain.models.CurrentWeatherResponse
import com.evg.weather_api.domain.models.CurrentWeatherSysResponse
import com.evg.weather_api.domain.models.CurrentWeatherWindResponse
import com.evg.weather_city.domain.model.CurrentWeather
import com.evg.weather_city.domain.model.CurrentWeatherClouds
import com.evg.weather_city.domain.model.CurrentWeatherInfo
import com.evg.weather_city.domain.model.CurrentWeatherMain
import com.evg.weather_city.domain.model.CurrentWeatherSys
import com.evg.weather_city.domain.model.CurrentWeatherWind

internal fun CurrentWeatherResponse.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        //coordinates = coordinates.toCurrentWeatherCoordinates(),
        weather = weather.map { it.toCurrentWeatherInfo() },
        main = main.toCurrentWeatherMain(),
        visibility = visibility,
        wind = wind.toCurrentWeatherWind(),
        //rain = rain?.toCurrentWeatherRain(),
        //snow = snow?.toCurrentWeatherSnow(),
        clouds = clouds.toCurrentWeatherClouds(),
        timestamp = timestamp,
        sys = sys.toCurrentWeatherSys(),
        timezone = timezone,
        id = id,
        name = name
    )
}

/*internal fun CurrentWeatherCoordinatesResponse.toCurrentWeatherCoordinates(): CurrentWeatherCoordinates {
    return CurrentWeatherCoordinates(
        lon = lon,
        lat = lat
    )
}*/

internal fun CurrentWeatherInfoResponse.toCurrentWeatherInfo(): CurrentWeatherInfo {
    return CurrentWeatherInfo(
        id = id,
        main = main,
        description = description,
        icon = icon
    )
}

internal fun CurrentWeatherMainResponse.toCurrentWeatherMain(): CurrentWeatherMain {
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

internal fun CurrentWeatherWindResponse.toCurrentWeatherWind(): CurrentWeatherWind {
    return CurrentWeatherWind(
        speed = speed,
        deg = deg,
        gust = gust
    )
}

/*internal fun CurrentWeatherRainResponse.toCurrentWeatherRain(): CurrentWeatherRain {
    return CurrentWeatherRain(
        oneHour = oneHour,
        threeHour = threeHour
    )
}

internal fun CurrentWeatherSnowResponse.toCurrentWeatherSnow(): CurrentWeatherSnow {
    return CurrentWeatherSnow(
        oneHour = oneHour,
        threeHour = threeHour
    )
}*/

internal fun CurrentWeatherCloudsResponse.toCurrentWeatherClouds(): CurrentWeatherClouds {
    return CurrentWeatherClouds(
        cloudiness = cloudiness
    )
}

internal fun CurrentWeatherSysResponse.toCurrentWeatherSys(): CurrentWeatherSys {
    return CurrentWeatherSys(
        country = country,
        sunrise = sunrise,
        sunset = sunset
    )
}
