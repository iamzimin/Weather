package com.evg.selection_city.domain.mapper

import com.evg.database.domain.models.CurrentWeatherDBO
import com.evg.selection_city.domain.model.CityInfo

internal fun CurrentWeatherDBO.toCityInfo(): CityInfo {
    return CityInfo(
        id = id,
        city = name,
        skyDescription = weather[0].main,
        temp = main.temp,
        tempMax = main.tempMax,
        tempMin = main.tempMin
    )
}
