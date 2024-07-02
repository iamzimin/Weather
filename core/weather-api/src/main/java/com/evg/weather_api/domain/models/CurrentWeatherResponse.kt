package com.evg.weather_api.domain.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("coord") val coordinates: CurrentWeatherCoordinatesResponse,
    @SerializedName("weather") val weather: List<CurrentWeatherInfoResponse>,
    //@SerializedName("base") val base: String, // Internal parameter
    @SerializedName("main") val main: CurrentWeatherMainResponse,
    @SerializedName("visibility") val visibility: Int, // Visibility, meter. The maximum value of the visibility is 10 km
    @SerializedName("wind") val wind: CurrentWeatherWindResponse,
    @SerializedName("rain") val rain: CurrentWeatherRainResponse?,
    @SerializedName("snow") val snow: CurrentWeatherSnowResponse?,
    @SerializedName("clouds") val clouds: CurrentWeatherCloudsResponse,
    @SerializedName("dt") val timestamp: Long, // Time of data calculation, unix, UTC
    @SerializedName("sys") val sys: CurrentWeatherSysResponse,
    @SerializedName("timezone") val timezone: Int, // Shift in seconds from UTC
    @SerializedName("id") val id: Int, // City ID. Please note that built-in geocoder functionality has been deprecated.
    @SerializedName("name") val name: String, // City name. Please note that built-in geocoder functionality has been deprecated.
    //@SerializedName("cod") val cod: Int // Response code
)


data class CurrentWeatherCoordinatesResponse(
    @SerializedName("lon") val lon: Double, // Longitude of the location
    @SerializedName("lat") val lat: Double // Latitude of the location
)

data class CurrentWeatherInfoResponse(
    @SerializedName("id") val id: Int, // Weather condition id
    @SerializedName("main") val main: String, // Group of weather parameters (Rain, Snow, Clouds etc.)
    @SerializedName("description") val description: String, // Weather condition within the group.
    @SerializedName("icon") val icon: String // Weather icon id
)

data class CurrentWeatherMainResponse(
    @SerializedName("temp") val temp: Double, // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    @SerializedName("feels_like") val feelsLike: Double, // Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    @SerializedName("temp_min") val tempMin: Double, // Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas).
    @SerializedName("temp_max") val tempMax: Double, // Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas).
    @SerializedName("pressure") val pressure: Int, // Atmospheric pressure on the sea level, hPa
    @SerializedName("humidity") val humidity: Int, // Humidity, %
    @SerializedName("sea_level") val seaLevel: Int, // Atmospheric pressure on the sea level, hPa
    @SerializedName("grnd_level") val groundLevel: Int // Atmospheric pressure on the ground level, hPa
)

data class CurrentWeatherWindResponse(
    @SerializedName("speed") val speed: Double, // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    @SerializedName("deg") val deg: Int, // Wind direction, degrees (meteorological)
    @SerializedName("gust") val gust: Double // Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
)

data class CurrentWeatherRainResponse(
    @SerializedName("1h") val oneHour: Double, // Rain volume for the last 1 hour, mm. Please note that only mm as units of measurement are available for this parameter
    @SerializedName("3h") val threeHour: Double, // Rain volume for the last 3 hours, mm. Please note that only mm as units of measurement are available for this parameter
)

data class CurrentWeatherSnowResponse(
    @SerializedName("1h") val oneHour: Double, // Snow volume for the last 1 hour, mm. Please note that only mm as units of measurement are available for this parameter
    @SerializedName("3h") val threeHour: Double, // Snow volume for the last 3 hour, mm. Please note that only mm as units of measurement are available for this parameter
)

data class CurrentWeatherCloudsResponse(
    @SerializedName("all") val cloudiness: Int // Cloudiness, %
)

data class CurrentWeatherSysResponse(
    @SerializedName("country") val country: String, // Country code (GB, JP etc.)
    @SerializedName("sunrise") val sunrise: Long, // Sunrise time, unix, UTC
    @SerializedName("sunset") val sunset: Long // Sunset time, unix, UTC
)
