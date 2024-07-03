package com.evg.database.domain.converter

import androidx.room.TypeConverter
import com.evg.database.domain.models.CurrentWeatherCloudsDBO
import com.evg.database.domain.models.CurrentWeatherInfoDBO
import com.evg.database.domain.models.CurrentWeatherMainDBO
import com.evg.database.domain.models.CurrentWeatherSysDBO
import com.evg.database.domain.models.CurrentWeatherWindDBO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CurrentWeatherConverter {
    @TypeConverter
    fun fromCurrentWeatherInfoList(weather: List<CurrentWeatherInfoDBO>): String {
        return Json.encodeToString(weather)
    }

    @TypeConverter
    fun toCurrentWeatherInfoList(weatherString: String): List<CurrentWeatherInfoDBO> {
        return Json.decodeFromString(weatherString)
    }

    @TypeConverter
    fun fromCurrentWeatherMain(main: CurrentWeatherMainDBO): String {
        return Json.encodeToString(main)
    }

    @TypeConverter
    fun toCurrentWeatherMain(mainString: String): CurrentWeatherMainDBO {
        return Json.decodeFromString(mainString)
    }

    @TypeConverter
    fun fromCurrentWeatherWind(wind: CurrentWeatherWindDBO): String {
        return Json.encodeToString(wind)
    }

    @TypeConverter
    fun toCurrentWeatherWind(windString: String): CurrentWeatherWindDBO {
        return Json.decodeFromString(windString)
    }

    @TypeConverter
    fun fromCurrentWeatherClouds(clouds: CurrentWeatherCloudsDBO): String {
        return Json.encodeToString(clouds)
    }

    @TypeConverter
    fun toCurrentWeatherClouds(cloudsString: String): CurrentWeatherCloudsDBO {
        return Json.decodeFromString(cloudsString)
    }

    @TypeConverter
    fun fromCurrentWeatherSys(sys: CurrentWeatherSysDBO): String {
        return Json.encodeToString(sys)
    }

    @TypeConverter
    fun toCurrentWeatherSys(sysString: String): CurrentWeatherSysDBO {
        return Json.decodeFromString(sysString)
    }
}
