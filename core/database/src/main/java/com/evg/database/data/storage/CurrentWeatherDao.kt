package com.evg.database.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evg.database.domain.models.CurrentWeatherDBO

@Dao
interface CurrentWeatherDao {
    @Query("SELECT * FROM currentweatherdbo")
    suspend fun getAllCurrentWeathers(): List<CurrentWeatherDBO>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeather: CurrentWeatherDBO)

    @Query("SELECT * FROM currentweatherdbo WHERE id = :id")
    suspend fun getCurrentWeatherById(id: Int): CurrentWeatherDBO?

    @Query("DELETE FROM currentweatherdbo WHERE id = :id")
    suspend fun deleteCurrentWeatherById(id: Int)
}