package com.evg.database.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evg.database.domain.converter.WeeklyForecastConverter
import com.evg.database.domain.models.CurrentWeatherDBO
import com.evg.database.domain.models.WeeklyForecastDBO

@Database(
    entities = [WeeklyForecastDBO::class],
    version = 1
)
@TypeConverters(WeeklyForecastConverter::class)
abstract class WeeklyForecastDatabase: RoomDatabase() {
    abstract val weeklyForecastDao: WeeklyForecastDao

    companion object {
        const val DATABASE_NAME = "weekly_forecast"
    }
}