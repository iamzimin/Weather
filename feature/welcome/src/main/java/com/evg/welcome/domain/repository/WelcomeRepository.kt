package com.evg.welcome.domain.repository

import com.evg.welcome.domain.model.City
import kotlinx.coroutines.flow.Flow

interface WelcomeRepository {
    suspend fun getCityList(): Flow<List<City>?>
    fun getCityByName(name: String): Flow<City?>
}