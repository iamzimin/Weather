package com.evg.database.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evg.database.domain.models.CityDBO

@Dao
interface CityDao {
    @Query("SELECT * FROM citydbo")
    suspend fun getAllCities(): List<CityDBO>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityDBO>)

    @Query("SELECT * FROM citydbo WHERE id = :id")
    suspend fun getCityById(id: Int): CityDBO?

    @Query("SELECT * FROM citydbo WHERE LOWER(name) = LOWER(:name) LIMIT 1")
    suspend fun getCityByName(name: String): CityDBO?
}