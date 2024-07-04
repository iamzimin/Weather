package com.evg.welcome.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evg.welcome.domain.model.City
import com.evg.welcome.domain.usecase.WelcomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val welcomeUseCases: WelcomeUseCases,
): ViewModel() {
    private val _cityList = MutableStateFlow<List<City>?>(null)
    val cityList: StateFlow<List<City>?> get() = _cityList

    private val _isCityListLoading = MutableStateFlow(true)
    val isCityListLoading: StateFlow<Boolean> = _isCityListLoading

    private val _city = MutableSharedFlow<City?>()
    val city = _city.asSharedFlow()

    val typedCityString = MutableStateFlow<String?>(null)
    val selectedCity = MutableStateFlow<City?>(null)


    fun navigateCity() {
        viewModelScope.launch {
            if (typedCityString.value != null) {
                typedCityString.value?.let {
                    welcomeUseCases.getCityByNameUseCase.invoke(name = it)
                        .collect { city ->
                            if (city == null) {
                                Toast.makeText(context, "City not found", Toast.LENGTH_SHORT).show()
                            }
                            _city.emit(city)
                        }
                }
            } else if (selectedCity.value != null) {
                _city.emit(selectedCity.value)
            }
        }
    }

    fun getCitiesList() {
        viewModelScope.launch {
            _isCityListLoading.value = true
            welcomeUseCases.getCityListUseCase.invoke()
                .collect { cities ->
                    _cityList.value = cities
                    _isCityListLoading.value = false
                }
        }
    }
}