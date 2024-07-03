package com.evg.selection_city.domain.usecase

import com.evg.selection_city.domain.repository.SelectionCityRepository
import javax.inject.Inject

class GetLatestCityUseCase @Inject constructor(
    private val selectionCityRepository: SelectionCityRepository
) {
    fun invoke(): Int? {
        return selectionCityRepository.getLatestCity()
    }
}