package com.evg.weather_api.domain.mapper

import com.evg.database.domain.models.CityDBO
import com.evg.weather_api.domain.models.CityResponse

fun CityResponse.toCityDBO(): CityDBO {
    return CityDBO(
        id = this.id,
        coordLon = this.coord.lon,
        coordLat = this.coord.lat,
        name = this.name,
    )
}