package com.evg.selection_city.domain.usecase

import com.evg.selection_city.domain.model.CityInfo
import com.evg.selection_city.domain.repository.SelectionCityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyCitiesListUseCase @Inject constructor(
    private val selectionCityRepository: SelectionCityRepository
) {
    fun invoke(): Flow<List<CityInfo>?> {
        return selectionCityRepository.getMyCitesList()
    }
}