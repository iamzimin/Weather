package com.evg.selection_city.domain.model

data class CityInfo(
    val id: Int,
    val city: String,
    val skyDescription: String,
    val weatherIcon: String,
    val temp: Double,
    val tempMax: Double,
    val tempMin: Double,
)
