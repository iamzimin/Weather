package com.evg.selection_city.domain.usecase

import com.evg.selection_city.domain.model.City
import com.evg.selection_city.domain.repository.SelectionCityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesListUseCase @Inject constructor(
    private val selectionCityRepository: SelectionCityRepository
) {
    fun invoke(): Flow<List<City>?> {
        return selectionCityRepository.getCitiesList()
    }
}