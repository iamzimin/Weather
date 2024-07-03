package com.evg.selection_city.domain.usecase

import javax.inject.Inject

data class SelectionCityUseCases @Inject constructor(
    val getCitiesListUseCase: GetCitiesListUseCase,
    val getCityByNameUseCase: GetCityByNameUseCase,
    val getMyCitiesListUseCase: GetMyCitiesListUseCase,
    val deleteCityByIdUseCase: DeleteCityByIdUseCase,
    val getLatestCityUseCase: GetLatestCityUseCase,
)
