package com.evg.database.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evg.database.domain.converter.CurrentWeatherConverter
import com.evg.database.domain.models.CurrentWeatherDBO

@Database(
    entities = [CurrentWeatherDBO::class],
    version = 1
)
@TypeConverters(CurrentWeatherConverter::class)
abstract class CurrentWeatherDatabase: RoomDatabase() {
    abstract val currentWeatherDao: CurrentWeatherDao

    companion object {
        const val DATABASE_NAME = "current_weather"
    }
}