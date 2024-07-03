package com.evg.selection_city.presentation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.selection_city.presentation.viewmodel.SelectionCityViewModel

@Composable
fun SelectionCityScreen(
    viewModel: SelectionCityViewModel = hiltViewModel<SelectionCityViewModel>(),
) {
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val cities by viewModel.cityList.collectAsState()
    val myCities by viewModel.myCityList.collectAsState()
    val isCityListLoading by viewModel.isCityListLoading.collectAsState()

    val city by viewModel.city.collectAsState()

    if (!isInitialized) {
        LaunchedEffect(Unit) {
            viewModel.getCitiesList()
            viewModel.getMyCityList()
            isInitialized = true
        }
    }

    if (cities == null && myCities == null) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    } else {
        SelectionCityContent(
            cityInfo = myCities!!, //TODO
            listCities = cities,
        )
    }

}