package com.evg.weather_api.domain.mapper

import com.evg.database.domain.models.CurrentWeatherCloudsDBO
import com.evg.database.domain.models.CurrentWeatherDBO
import com.evg.database.domain.models.CurrentWeatherInfoDBO
import com.evg.database.domain.models.CurrentWeatherMainDBO
import com.evg.database.domain.models.CurrentWeatherSysDBO
import com.evg.database.domain.models.CurrentWeatherWindDBO
import com.evg.weather_api.domain.models.CurrentWeatherCloudsResponse
import com.evg.weather_api.domain.models.CurrentWeatherInfoResponse
import com.evg.weather_api.domain.models.CurrentWeatherMainResponse
import com.evg.weather_api.domain.models.CurrentWeatherResponse
import com.evg.weather_api.domain.models.CurrentWeatherSysResponse
import com.evg.weather_api.domain.models.CurrentWeatherWindResponse

fun CurrentWeatherResponse.toCurrentWeatherDBO(): CurrentWeatherDBO {
    return CurrentWeatherDBO(
        id = this.id,
        weather = this.weather.map { it.toCurrentWeatherInfoDBO() },
        main = this.main.toCurrentWeatherMainDBO(),
        visibility = this.visibility,
        wind = this.wind.toCurrentWeatherWindDBO(),
        clouds = this.clouds.toCurrentWeatherCloudsDBO(),
        timestamp = this.timestamp,
        sys = this.sys.toCurrentWeatherSysDBO(),
        timezone = this.timezone,
        name = this.name
    )
}

fun CurrentWeatherInfoResponse.toCurrentWeatherInfoDBO(): CurrentWeatherInfoDBO {
    return CurrentWeatherInfoDBO(
        id = this.id,
        main = this.main,
        description = this.description,
        icon = this.icon
    )
}

fun CurrentWeatherMainResponse.toCurrentWeatherMainDBO(): CurrentWeatherMainDBO {
    return CurrentWeatherMainDBO(
        temp = this.temp.toInt(),
        feelsLike = this.feelsLike.toInt(),
        tempMin = this.tempMin.toInt(),
        tempMax = this.tempMax.toInt(),
        pressure = this.pressure,
        humidity = this.humidity,
        seaLevel = this.seaLevel,
        groundLevel = this.groundLevel
    )
}

fun CurrentWeatherWindResponse.toCurrentWeatherWindDBO(): CurrentWeatherWindDBO {
    return CurrentWeatherWindDBO(
        speed = this.speed,
        deg = this.deg,
        gust = this.gust
    )
}

fun CurrentWeatherCloudsResponse.toCurrentWeatherCloudsDBO(): CurrentWeatherCloudsDBO {
    return CurrentWeatherCloudsDBO(
        cloudiness = this.cloudiness
    )
}

fun CurrentWeatherSysResponse.toCurrentWeatherSysDBO(): CurrentWeatherSysDBO {
    return CurrentWeatherSysDBO(
        country = this.country,
        sunrise = this.sunrise,
        sunset = this.sunset
    )
}