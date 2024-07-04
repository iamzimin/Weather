package com.evg.weather_api.domain.models

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("coord") val coordinates: CityCoordinates,
    @SerializedName("country") val country: String,
    @SerializedName("name") val name: String,
)

data class CityCoordinates(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)