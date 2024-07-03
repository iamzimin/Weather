package com.evg.welcome.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evg.welcome.domain.model.City
import com.evg.welcome.domain.usecase.WelcomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _city = MutableStateFlow<City?>(null)
    val city: StateFlow<City?> get() = _city


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

    fun checkCity(name: String?) {
        viewModelScope.launch {
            if (name == null) {
                _city.value = null
                showToast("City not found")
                return@launch
            }

            welcomeUseCases.getCityByNameUseCase.invoke(name = name)
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

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}