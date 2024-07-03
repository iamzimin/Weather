package com.evg.welcome.domain.usecase

import com.evg.welcome.domain.model.City
import com.evg.welcome.domain.repository.WelcomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityByNameUseCase @Inject constructor(
    private val welcomeRepository: WelcomeRepository
) {
    fun invoke(name: String): Flow<City?> {
        return welcomeRepository.getCityByName(name = name)
    }
}