package com.evg.weather_city.presentation.model

data class HourlyForecastUI(
    val timestamp: Long,
    val temp: Int,
    val icon: String,
) {
    fun getIconUrl(): String {
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }
}