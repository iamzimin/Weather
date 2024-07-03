package com.evg.weather_city.domain.model

data class WeeklyForecast(
    //val cod: String,
    //val message: Int,
    //val cnt: Int,
    val weatherList: List<WeeklyForecastWeather>,
    val city: WeeklyForecastCityInfo
)

data class WeeklyForecastWeather(
    val dt: Long,
    val main: WeeklyForecastMain,

    //val weather: List<WeeklyForecastWeatherInfo>,
    val weatherId: Int,
    val weatherMain: String,
    val weatherIcon: String

    //val clouds: WeeklyForecastClouds,
    //val wind: WeeklyForecastWind,
    //val visibility: Int,
    //val pop: Double,
    //val sys: WeeklyForecastSys,
    //val dtTxt: String
)

data class WeeklyForecastMain(
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

/*data class WeeklyForecastWeatherInfo(
    val id: Int,
    val main: String,
    //val description: String,
    val icon: String
)*/

/*data class WeeklyForecastClouds(
    val all: Int
)*/

/*data class WeeklyForecastWind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)*/

/*data class WeeklyForecastSys(
    val pod: String
)*/

data class WeeklyForecastCityInfo(
    val id: Int,
    val name: String,
    //val coordinates: WeeklyForecastCoordinates,
    //val country: String,
    //val population: Int,
    val timezone: Int,
    //val sunrise: Long,
    //val sunset: Long
)

/*data class WeeklyForecastCoordinates(
    val lat: Double,
    val lon: Double
)*/
