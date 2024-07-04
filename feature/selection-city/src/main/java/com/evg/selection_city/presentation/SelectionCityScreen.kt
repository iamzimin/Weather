package com.evg.selection_city.presentation

import android.widget.Toast
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.resource.LocalNavHostController
import com.evg.selection_city.presentation.viewmodel.SelectionCityViewModel

@Composable
fun SelectionCityScreen(
    viewModel: SelectionCityViewModel = hiltViewModel<SelectionCityViewModel>(),
) {
    val context = LocalContext.current
    val navController = LocalNavHostController.current
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val cities by viewModel.cityList.collectAsState()
    val myCities by viewModel.myCityList.collectAsState()
    val latestCityId by viewModel.latestCityId.collectAsState()
    val isMyCitiesListLoading by viewModel.isMyCitiesListLoading.collectAsState()

    val city by viewModel.city.collectAsState(initial = null)

    if (!isInitialized) {
        LaunchedEffect(Unit) {
            viewModel.getCitiesList()
            viewModel.getMyCityList()
            isInitialized = true
        }
    }

    if (city != null) {
        navController.navigate("city/${city?.id ?: -1}")
    }

    if (isMyCitiesListLoading) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    } else {
        myCities?.let { mc ->
            SelectionCityContent(
                cityInfo = mc,
                listCities = cities,
                currentCityId = latestCityId,
                deleteCity = {
                    viewModel.deleteCityById(id = it)
                },
                setCityString = { name ->
                    viewModel.typedCityString.value = name
                },
                setCity = { newCity ->
                    viewModel.selectedCity.value = newCity
                },
                onCityApply = {
                    viewModel.navigateCity()
                }
            )
        }
    }

}