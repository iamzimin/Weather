package com.evg.selection_city.domain.utils

import kotlin.math.roundToInt

internal fun Double.kelvinToCelsius(): Int {
    return (this - 273.15).roundToInt()
}