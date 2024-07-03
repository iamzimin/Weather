package com.evg.database.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class CurrentWeatherDBO(
    @PrimaryKey val id: Int,
    //val coordinates: CurrentWeatherCoordinatesDBO,
    val weather: List<CurrentWeatherInfoDBO>,
    // val base: String,
    val main: CurrentWeatherMainDBO,
    val visibility: Int,
    val wind: CurrentWeatherWindDBO,
    //val rain: CurrentWeatherRainDBO?,
    //val snow: CurrentWeatherSnowDBO?,
    val clouds: CurrentWeatherCloudsDBO,
    val timestamp: Long,
    val sys: CurrentWeatherSysDBO,
    val timezone: Int,
    val name: String,
    // val cod: Int
)

/*data class CurrentWeatherCoordinatesDBO(
    val lon: Double, 
    val lat: Double 
)*/

@Serializable
data class CurrentWeatherInfoDBO(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class CurrentWeatherMainDBO(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    val seaLevel: Int,
    val groundLevel: Int
)

@Serializable
data class CurrentWeatherWindDBO(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

/*data class CurrentWeatherRainDBO(
    val oneHour: Double, 
    val threeHour: Double, 
)

data class CurrentWeatherSnowDBO(
    val oneHour: Double, 
    val threeHour: Double, 
)*/

@Serializable
data class CurrentWeatherCloudsDBO(
    val cloudiness: Int
)

@Serializable
data class CurrentWeatherSysDBO(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
