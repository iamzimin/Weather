package com.evg.weather_city.presentation.mapper

import kotlin.math.roundToInt


internal fun Double.kelvinToCelsius(): Int {
    return (this - 273.15).roundToInt()
}