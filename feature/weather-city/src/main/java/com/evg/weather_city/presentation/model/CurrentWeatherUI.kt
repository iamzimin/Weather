package com.evg.weather_city.presentation.model

import org.intellij.lang.annotations.Pattern
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class CurrentWeatherUI(
    //val coordinates: CurrentWeatherCoordinatesUI,
    val weather: List<CurrentWeatherInfoUI>,
    // val base: String,
    val main: CurrentWeatherMainUI,
    val visibility: Int, 
    val wind: CurrentWeatherWindUI,
    //val rain: CurrentWeatherRainUI?,
    //val snow: CurrentWeatherSnowUI?,
    val clouds: CurrentWeatherCloudsUI,
    val timestamp: Long, 
    val sys: CurrentWeatherSysUI,
    val timezone: Int, 
    val id: Int, 
    val name: String,
    // val cod: Int
)

/*data class CurrentWeatherCoordinatesUI(
    val lon: Double, 
    val lat: Double 
)*/

data class CurrentWeatherInfoUI(
    val id: Int, 
    val main: String, 
    val description: String, 
    val icon: String 
)

data class CurrentWeatherMainUI(
    val temp: Int, 
    val feelsLike: Int, 
    val tempMin: Int, 
    val tempMax: Int, 
    val pressure: Int, 
    val humidity: Int, 
    val seaLevel: Int, 
    val groundLevel: Int 
)

data class CurrentWeatherWindUI(
    val speed: Double, 
    val deg: Int, 
    val gust: Double 
)

/*data class CurrentWeatherRainUI(
    val oneHour: Double, 
    val threeHour: Double, 
)

data class CurrentWeatherSnowUI(
    val oneHour: Double, 
    val threeHour: Double, 
)*/

data class CurrentWeatherCloudsUI(
    val cloudiness: Int 
)

data class CurrentWeatherSysUI(
    val country: String, 
    val sunrise: Long, 
    val sunset: Long 
)
