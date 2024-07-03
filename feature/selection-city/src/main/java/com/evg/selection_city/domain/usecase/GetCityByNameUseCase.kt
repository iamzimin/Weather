package com.evg.selection_city.domain.usecase

import com.evg.selection_city.domain.model.City
import com.evg.selection_city.domain.repository.SelectionCityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityByNameUseCase @Inject constructor(
    private val selectionCityRepository: SelectionCityRepository
) {
    fun invoke(name: String): Flow<City?> {
        return selectionCityRepository.getCityByName(name = name)
    }
}