package com.evg.weather_api.domain.mapper

import com.evg.database.domain.models.CityDBO
import com.evg.weather_api.domain.models.CityResponse

fun CityResponse.toCityDBO(): CityDBO {
    return CityDBO(
        id = this.id,
        coordLon = this.coordinates.lon,
        coordLat = this.coordinates.lat,
        name = this.name,
    )
}