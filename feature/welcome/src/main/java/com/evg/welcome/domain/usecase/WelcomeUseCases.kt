package com.evg.welcome.domain.usecase

import javax.inject.Inject

data class WelcomeUseCases @Inject constructor(
    val getCityList: GetCityList,
    val getCityByName: GetCityByName,
)