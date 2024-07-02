package com.evg.weather_city.presentation.model

data class DailyForecastUI(
    val timestamp: Long,
    val icon: String,
    val weatherDescription: String,
    val tempMin: Int,
    val tempMax: Int,
) {
    fun getIconUrl(): String {
        return "https://openweathermap.org/img/wn/${icon}@2x.png"
    }
}