package com.evg.selection_city.domain.usecase

import com.evg.selection_city.domain.repository.SelectionCityRepository
import javax.inject.Inject

class DeleteCityByIdUseCase @Inject constructor(
    private val selectionCityRepository: SelectionCityRepository
) {
    suspend fun invoke(id: Int) {
        selectionCityRepository.deleteCityById(id = id)
    }
}