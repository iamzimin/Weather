package com.evg.database.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evg.database.domain.models.CurrentWeatherDBO
import com.evg.database.domain.models.WeeklyForecastDBO

@Dao
interface WeeklyForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeklyForecast(weeklyForecast: WeeklyForecastDBO)

    @Query("SELECT * FROM weeklyforecastdbo WHERE id = :id")
    suspend fun getWeeklyForecastById(id: Int): WeeklyForecastDBO?
}