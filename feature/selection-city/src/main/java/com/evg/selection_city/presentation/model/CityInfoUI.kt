package com.evg.selection_city.presentation.model

data class CityInfoUI(
    val id: Int,
    val city: String,
    val skyDescription: String,
    val weatherIcon: String,
    val temp: Int,
    val tempMax: Int,
    val tempMin: Int,
) {
    fun getIconUrl(): String {
        return "https://openweathermap.org/img/wn/${weatherIcon}@2x.png"
    }
}
