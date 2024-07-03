package com.evg.database.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class WeeklyForecastDBO(
    @PrimaryKey val id: Int,
    //val cod: String,
    //val message: Int,
    //val cnt: Int,
    val weatherList: List<WeeklyForecastWeatherDBO>,
    val city: WeeklyForecastCityInfoDBO
)

@Serializable
data class WeeklyForecastWeatherDBO(
    val dt: Long,
    val main: WeeklyForecastMainDBO,

    //val weather: List<WeeklyForecastWeatherInfoDBO>,
    val weatherId: Int,
    val weatherMain: String,
    val weatherIcon: String

    //val clouds: WeeklyForecastCloudsDBO,
    //val wind: WeeklyForecastWindDBO,
    //val visibility: Int,
    //val pop: Double,
    //val sys: WeeklyForecastSysDBO,
    //val dtTxt: String
)

@Serializable
data class WeeklyForecastMainDBO(
    val temp: Double,
    //val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    //val pressure: Int,
    //val seaLevel: Int,
    //val groundLevel: Int,
    //val humidity: Int,
    //val tempKf: Double
)

/*data class WeeklyForecastWeatherInfoDBO(
    val id: Int,
    val main: String,
    //val description: String,
    val icon: String
)*/

/*data class WeeklyForecastCloudsDBO(
    val all: Int
)*/

/*data class WeeklyForecastWindDBO(
    val speed: Double,
    val deg: Int,
    val gust: Double
)*/

/*data class WeeklyForecastSysDBO(
    val pod: String
)*/

@Serializable
data class WeeklyForecastCityInfoDBO(
    //val id: Int, // Moved to PrimaryKey
    val name: String,
    //val coordinates: WeeklyForecastCoordinatesDBO,
    //val country: String,
    //val population: Int,
    val timezone: Int,
    //val sunrise: Long,
    //val sunset: Long
)

/*data class WeeklyForecastCoordinatesDBO(
    val lat: Double,
    val lon: Double
)*/
