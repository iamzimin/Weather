package com.evg.weather_api.domain.models

import com.google.gson.annotations.SerializedName

data class WeeklyForecastResponse(
    @SerializedName("cod") val cod: String,
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val weatherList: List<WeeklyForecastWeatherResponse>,
    @SerializedName("city") val city: WeeklyForecastCityInfoResponse
)


data class WeeklyForecastWeatherResponse(
    @SerializedName("dt") val dt: Long,
    @SerializedName("main") val main: WeeklyForecastMainResponse,
    @SerializedName("weather") val weather: List<WeeklyForecastWeatherInfoResponse>,
    @SerializedName("clouds") val clouds: WeeklyForecastCloudsResponse,
    @SerializedName("wind") val wind: WeeklyForecastWindResponse,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("pop") val pop: Double,
    @SerializedName("sys") val sys: WeeklyForecastSysResponse,
    @SerializedName("dt_txt") val dtTxt: String
)

data class WeeklyForecastMainResponse(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("grnd_level") val grndLevel: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_kf") val tempKf: Double
)

data class WeeklyForecastWeatherInfoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class WeeklyForecastCloudsResponse(
    @SerializedName("all") val all: Int
)

data class WeeklyForecastWindResponse(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust: Double
)

data class WeeklyForecastSysResponse(
    @SerializedName("pod") val pod: String
)

data class WeeklyForecastCityInfoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: WeeklyForecastCoordResponse,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Int,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)

data class WeeklyForecastCoordResponse(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)
