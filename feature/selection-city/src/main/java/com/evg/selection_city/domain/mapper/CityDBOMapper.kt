package com.evg.selection_city.domain.mapper

import com.evg.database.domain.models.CityDBO
import com.evg.selection_city.domain.model.City
import com.evg.weather_api.domain.models.CityResponse

internal fun CityResponse.toCity(): City {
    return City(
        id = this.id,
        coordLon = this.coordinates.lon,
        coordLat = this.coordinates.lat,
        name = this.name,
    )
}

internal fun CityDBO.toCity(): City {
    return City(
        id = this.id,
        coordLon = this.coordLon,
        coordLat = this.coordLat,
        name = this.name,
    )
}