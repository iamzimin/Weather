package com.evg.welcome.domain.usecase

import com.evg.welcome.domain.model.City
import com.evg.welcome.domain.repository.WelcomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityList @Inject constructor(
    private val welcomeRepository: WelcomeRepository
) {
    suspend fun invoke(): Flow<List<City>?> {
        return welcomeRepository.getCityList()
    }
}