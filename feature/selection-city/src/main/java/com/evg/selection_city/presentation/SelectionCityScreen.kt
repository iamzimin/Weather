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
        if (city != null) {
            Toast.makeText(context, "Navigating to ${city?.name}", Toast.LENGTH_SHORT).show()
            val id = city?.id ?: -1
            navController.navigate("city/${id}")
        }

        SelectionCityContent(
            cityInfo = myCities!!, //TODO
            listCities = cities,
            deleteCity = {
                viewModel.deleteCityById(id = it)
            },
            checkCity = { name ->
                viewModel.checkCity(name)
            },
            setCity = { newCity ->
                viewModel.setSelectedCity(newCity)
            },
            onCityApply = {
                //TODO
                if (viewModel.city.value != null) {
                    Toast.makeText(context, "Navigating to ${viewModel.city.value}", Toast.LENGTH_SHORT).show()
                    val id = city?.id ?: -1
                    navController.navigate("city/${id}")
                }
            }
        )
    }

}