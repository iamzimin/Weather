package com.evg.welcome.domain.mapper

import com.evg.database.domain.models.CityDBO
import com.evg.weather_api.domain.models.CityResponse
import com.evg.welcome.domain.model.City

fun CityResponse.toCity(): City {
    return City(
        id = this.id,
        coordLon = this.coordinates.lon,
        coordLat = this.coordinates.lat,
        name = this.name,
    )
}

fun CityDBO.toCity(): City {
    return City(
        id = this.id,
        coordLon = this.coordLon,
        coordLat = this.coordLat,
        name = this.name,
    )
}