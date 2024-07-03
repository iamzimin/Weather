package com.evg.welcome.domain.usecase

import javax.inject.Inject

data class WelcomeUseCases @Inject constructor(
    val getCityListUseCase: GetCityListUseCase,
    val getCityByNameUseCase: GetCityByNameUseCase,
)