package com.evg.weather_api.domain.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("coord") val coord: CurrentWeatherCoordResponse,
    @SerializedName("weather") val weather: List<CurrentWeatherInfoResponse>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: CurrentWeatherMainResponse,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: CurrentWeatherWindResponse,
    @SerializedName("clouds") val clouds: CurrentWeatherCloudsResponse,
    @SerializedName("dt") val dt: Long,
    @SerializedName("sys") val sys: CurrentWeatherSysResponse,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
)


data class CurrentWeatherCoordResponse(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class CurrentWeatherInfoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class CurrentWeatherMainResponse(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("grnd_level") val groundLevel: Int
)

data class CurrentWeatherWindResponse(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust: Double
)

data class CurrentWeatherCloudsResponse(
    @SerializedName("all") val all: Int
)

data class CurrentWeatherSysResponse(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)
