package com.evg.database.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evg.database.domain.models.CityDBO

@Database(
    entities = [CityDBO::class],
    version = 1
)
abstract class CityDatabase: RoomDatabase() {
    abstract val cityDao: CityDao

    companion object {
        const val DATABASE_NAME = "cities"
    }
}