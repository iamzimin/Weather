package com.evg.selection_city.presentation.mapper

import com.evg.resource.model.CityUI
import com.evg.selection_city.domain.model.City

internal fun CityUI.toCity(): City {
    return City(
        id = id,
        coordLon = coordLon,
        coordLat = coordLat,
        name = name,
    )
}

internal fun City.toCityUI(): CityUI {
    return CityUI(
        id = id,
        coordLon = coordLon,
        coordLat = coordLat,
        name = name,
    )
}