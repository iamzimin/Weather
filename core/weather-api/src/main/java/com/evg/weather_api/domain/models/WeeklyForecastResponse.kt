package com.evg.weather_api.domain.models

import com.google.gson.annotations.SerializedName

data class WeeklyForecastResponse(
    @SerializedName("cod") val cod: String, // Internal parameter
    @SerializedName("message") val message: Int, // Internal parameter
    @SerializedName("cnt") val cnt: Int, // A number of timestamps returned in the API response
    @SerializedName("list") val weatherList: List<WeeklyForecastWeatherResponse>,
    @SerializedName("city") val city: WeeklyForecastCityInfoResponse
)


data class WeeklyForecastWeatherResponse(
    @SerializedName("dt") val dt: Long, // Time of data forecasted, unix, UTC
    @SerializedName("main") val main: WeeklyForecastMainResponse,
    @SerializedName("weather") val weather: List<WeeklyForecastWeatherInfoResponse>,
    @SerializedName("clouds") val clouds: WeeklyForecastCloudsResponse,
    @SerializedName("wind") val wind: WeeklyForecastWindResponse,
    @SerializedName("visibility") val visibility: Int, // Average visibility, metres. The maximum value of the visibility is 10km
    @SerializedName("pop") val pop: Double, // Probability of precipitation. The values of the parameter vary between 0 and 1, where 0 is equal to 0%, 1 is equal to 100%
    @SerializedName("rain") val rain: WeeklyForecastRainResponse?,
    @SerializedName("snow") val snow: WeeklyForecastSnowResponse?,
    @SerializedName("sys") val sys: WeeklyForecastSysResponse,
    @SerializedName("dt_txt") val dtTxt: String // Time of data forecasted, ISO, UTC
)

data class WeeklyForecastMainResponse(
    @SerializedName("temp") val temp: Double, // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    @SerializedName("feels_like") val feelsLike: Double, // This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    @SerializedName("temp_min") val tempMin: Double, // Minimum temperature at the moment of calculation. This is minimal forecasted temperature (within large megalopolises and urban areas), use this parameter optionally.
    @SerializedName("temp_max") val tempMax: Double, // Maximum temperature at the moment of calculation. This is maximal forecasted temperature (within large megalopolises and urban areas), use this parameter optionally.
    @SerializedName("pressure") val pressure: Int, // Atmospheric pressure on the sea level by default, hPa
    @SerializedName("sea_level") val seaLevel: Int, // Atmospheric pressure on the sea level, hPa
    @SerializedName("grnd_level") val groundLevel: Int, // Atmospheric pressure on the ground level, hPa
    @SerializedName("humidity") val humidity: Int, // Humidity, %
    @SerializedName("temp_kf") val tempKf: Double // Internal parameter
)

data class WeeklyForecastWeatherInfoResponse(
    @SerializedName("id") val id: Int, // Weather condition id
    @SerializedName("main") val main: String, // Group of weather parameters (Rain, Snow, Clouds etc.)
    @SerializedName("description") val description: String, // Weather condition within the group.
    @SerializedName("icon") val icon: String // Weather icon id
)

data class WeeklyForecastCloudsResponse(
    @SerializedName("all") val all: Int // Cloudiness, %
)

data class WeeklyForecastWindResponse(
    @SerializedName("speed") val speed: Double, // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    @SerializedName("deg") val deg: Int, // Wind direction, degrees (meteorological)
    @SerializedName("gust") val gust: Double // Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
)

data class WeeklyForecastRainResponse(
    @SerializedName("1h") val oneHour: Double, // Rain volume for the last 1 hour, mm. Please note that only mm as units of measurement are available for this parameter
    @SerializedName("3h") val threeHour: Double, // Rain volume for the last 3 hours, mm. Please note that only mm as units of measurement are available for this parameter
)

data class WeeklyForecastSnowResponse(
    @SerializedName("1h") val oneHour: Double, // Snow volume for the last 1 hour, mm. Please note that only mm as units of measurement are available for this parameter
    @SerializedName("3h") val threeHour: Double, // Snow volume for the last 3 hour, mm. Please note that only mm as units of measurement are available for this parameter
)

data class WeeklyForecastSysResponse(
    @SerializedName("pod") val pod: String // Part of the day (n - night, d - day)
)

data class WeeklyForecastCityInfoResponse(
    @SerializedName("id") val id: Int, // City ID. Please note that built-in geocoder functionality has been deprecated.
    @SerializedName("name") val name: String, // City name. Please note that built-in geocoder functionality has been deprecated.
    @SerializedName("coord") val coordinates: WeeklyForecastCoordinatesResponse,
    @SerializedName("country") val country: String, // Country code (GB, JP etc.). Please note that built-in geocoder functionality has been deprecated.
    @SerializedName("population") val population: Int, // City population
    @SerializedName("timezone") val timezone: Int, // Shift in seconds from UTC
    @SerializedName("sunrise") val sunrise: Long, // Sunrise time, Unix, UTC
    @SerializedName("sunset") val sunset: Long // Sunset time, Unix, UTC
)

data class WeeklyForecastCoordinatesResponse(
    @SerializedName("lat") val lat: Double, // Geo location, latitude
    @SerializedName("lon") val lon: Double // Geo location, longitude
)
