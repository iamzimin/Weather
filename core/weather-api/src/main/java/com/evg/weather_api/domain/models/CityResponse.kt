package com.evg.weather_api.domain.models

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("coord") val coordinates: CityCoordinates,
    @SerializedName("country") val country: String,
    @SerializedName("geoname") val geoname: CityGeonameResponse,
    //@SerializedName("langs") val languages: List<CityLanguagesResponse>,
    @SerializedName("name") val name: String,
    @SerializedName("stat") val stat: CityStatisticsResponse,
    @SerializedName("stations") val stations: List<CityStationResponse>,
    @SerializedName("zoom") val zoom: Int
)

data class CityCoordinates(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class CityGeonameResponse(
    @SerializedName("cl") val cl: String,
    @SerializedName("code") val code: String,
    @SerializedName("parent") val parent: Int
)

/*data class CityLanguagesResponse(
    @SerializedName("de") val de: String?,
    @SerializedName("fa") val fa: String?
)*/

data class CityStatisticsResponse(
    @SerializedName("level") val level: Double,
    @SerializedName("population") val population: Int
)

data class CityStationResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("dist") val dist: Int,
    @SerializedName("kf") val kf: Int
)