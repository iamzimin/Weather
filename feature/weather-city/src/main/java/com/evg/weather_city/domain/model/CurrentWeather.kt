package com.evg.weather_city.domain.model

data class CurrentWeather(
    //val coordinates: CurrentWeatherCoordinates,
    val weather: List<CurrentWeatherInfo>,
    // val base: String, // Internal parameter
    val main: CurrentWeatherMain,
    val visibility: Int, // Visibility, meter. The maximum value of the visibility is 10 km
    val wind: CurrentWeatherWind,
    //val rain: CurrentWeatherRain?,
    //val snow: CurrentWeatherSnow?,
    val clouds: CurrentWeatherClouds,
    val timestamp: Long, // Time of data calculation, unix, UTC
    val sys: CurrentWeatherSys,
    val timezone: Int, // Shift in seconds from UTC
    val id: Int, // City ID. Please note that built-in geocoder functionality has been deprecated.
    val name: String, // City name. Please note that built-in geocoder functionality has been deprecated.
    // val cod: Int //  code
)


/*data class CurrentWeatherCoordinates(
    val lon: Double, // Longitude of the location
    val lat: Double // Latitude of the location
)*/

data class CurrentWeatherInfo(
    val id: Int, // Weather condition id
    val main: String, // Group of weather parameters (Rain, Snow, Clouds etc.)
    val description: String, // Weather condition within the group.
    val icon: String // Weather icon id
)

data class CurrentWeatherMain(
    val temp: Double, // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    val feelsLike: Double, // Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    val tempMin: Double, // Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas).
    val tempMax: Double, // Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas).
    val pressure: Int, // Atmospheric pressure on the sea level, hPa
    val humidity: Int, // Humidity, %
    val seaLevel: Int, // Atmospheric pressure on the sea level, hPa
    val groundLevel: Int // Atmospheric pressure on the ground level, hPa
)

data class CurrentWeatherWind(
    val speed: Double, // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
    val deg: Int, // Wind direction, degrees (meteorological)
    val gust: Double // Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
)

/*data class CurrentWeatherRain(
    val oneHour: Double, // Rain volume for the last 1 hour, mm. Please note that only mm as units of measurement are available for this parameter
    val threeHour: Double, // Rain volume for the last 3 hours, mm. Please note that only mm as units of measurement are available for this parameter
)

data class CurrentWeatherSnow(
    val oneHour: Double, // Snow volume for the last 1 hour, mm. Please note that only mm as units of measurement are available for this parameter
    val threeHour: Double, // Snow volume for the last 3 hour, mm. Please note that only mm as units of measurement are available for this parameter
)*/

data class CurrentWeatherClouds(
    val cloudiness: Int // Cloudiness, %
)

data class CurrentWeatherSys(
    val country: String, // Country code (GB, JP etc.)
    val sunrise: Long, // Sunrise time, unix, UTC
    val sunset: Long // Sunset time, unix, UTC
)
