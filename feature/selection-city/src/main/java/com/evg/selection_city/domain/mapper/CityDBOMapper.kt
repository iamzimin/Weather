package com.evg.selection_city.domain.mapper

import com.evg.database.domain.models.CityDBO
import com.evg.selection_city.domain.model.City

internal fun CityDBO.toCity(): City {
    return City(
        id = this.id,
        coordLon = this.coordLon,
        coordLat = this.coordLat,
        name = this.name,
    )
}