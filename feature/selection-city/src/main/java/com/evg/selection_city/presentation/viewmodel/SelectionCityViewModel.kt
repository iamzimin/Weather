package com.evg.selection_city.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evg.selection_city.domain.model.City
import com.evg.selection_city.domain.model.CityInfo
import com.evg.selection_city.domain.usecase.SelectionCityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionCityViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val selectionCityUseCases: SelectionCityUseCases,
): ViewModel() {
    private val _cityList = MutableStateFlow<List<City>?>(null)
    val cityList: StateFlow<List<City>?> get() = _cityList

    private val _isCityListLoading = MutableStateFlow(true)
    val isCityListLoading: StateFlow<Boolean> = _isCityListLoading

    private val _myCityList = MutableStateFlow<List<CityInfo>?>(null)
    val myCityList: StateFlow<List<CityInfo>?> get() = _myCityList

    private val _city = MutableStateFlow<City?>(null)
    val city: StateFlow<City?> get() = _city

    fun getCitiesList() {
        viewModelScope.launch {
            _isCityListLoading.value = true
            selectionCityUseCases.getCitiesListUseCase.invoke()
                .collect { cities ->
                    _cityList.value = cities
                    _isCityListLoading.value = false
                }
        }
    }

    fun getMyCityList() {
        viewModelScope.launch {
            selectionCityUseCases.getMyCitiesListUseCase.invoke()
                .collect { cities ->
                    _myCityList.value = cities
                }
        }
    }

    fun checkCity(name: String?) {
        viewModelScope.launch {
            if (name == null) {
                _city.value = null
                showToast("City not found")
                return@launch
            }

            selectionCityUseCases.getCityByNameUseCase.invoke(name = name)
                .collect { city ->
                    if (city == null) {
                        showToast("City not found")
                    } else {
                        showToast("Found $city")
                    }
                    _city.value = city
                }
        }
    }

    fun setSelectedCity(city: City?) {
        viewModelScope.launch {
            _city.value = city
        }
    }

    fun deleteCityById(id: Int) {
        viewModelScope.launch {
            selectionCityUseCases.deleteCityByIdUseCase.invoke(id = id)
            _myCityList.value = _myCityList.value?.filter { it.id != id }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}