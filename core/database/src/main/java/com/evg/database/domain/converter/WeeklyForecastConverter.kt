package com.evg.database.domain.converter

import androidx.room.TypeConverter
import com.evg.database.domain.models.WeeklyForecastCityInfoDBO
import com.evg.database.domain.models.WeeklyForecastMainDBO
import com.evg.database.domain.models.WeeklyForecastWeatherDBO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object WeeklyForecastConverter {
    @TypeConverter
    fun fromWeeklyForecastWeatherList(weatherList: List<WeeklyForecastWeatherDBO>): String {
        return Json.encodeToString(weatherList)
    }

    @TypeConverter
    fun toWeeklyForecastWeatherList(weatherListString: String): List<WeeklyForecastWeatherDBO> {
        return Json.decodeFromString(weatherListString)
    }

    @TypeConverter
    fun fromWeeklyForecastMain(main: WeeklyForecastMainDBO): String {
        return Json.encodeToString(main)
    }

    @TypeConverter
    fun toWeeklyForecastMain(mainString: String): WeeklyForecastMainDBO {
        return Json.decodeFromString(mainString)
    }

    @TypeConverter
    fun fromWeeklyForecastCityInfo(cityInfo: WeeklyForecastCityInfoDBO): String {
        return Json.encodeToString(cityInfo)
    }

    @TypeConverter
    fun toWeeklyForecastCityInfo(cityInfoString: String): WeeklyForecastCityInfoDBO {
        return Json.decodeFromString(cityInfoString)
    }
}