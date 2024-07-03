package com.evg.selection_city.presentation.mapper

import com.evg.selection_city.domain.model.CityInfo
import com.evg.selection_city.domain.utils.kelvinToCelsius
import com.evg.selection_city.presentation.model.CityInfoUI

internal fun CityInfo.toCityInfoUI(): CityInfoUI {
    return CityInfoUI(
        id = id,
        city = city,
        skyDescription = skyDescription,
        temp = temp.kelvinToCelsius(),
        tempMin = tempMin.kelvinToCelsius(),
        tempMax = tempMax.kelvinToCelsius(),
    )
}