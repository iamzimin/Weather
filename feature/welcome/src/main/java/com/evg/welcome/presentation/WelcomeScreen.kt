package com.evg.welcome.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.resource.LocalNavHostController
import com.evg.resource.theme.WeatherTheme
import com.evg.welcome.presentation.viewmodel.WelcomeViewModel

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel<WelcomeViewModel>(),
) {
    val context = LocalContext.current
    val navController = LocalNavHostController.current
    var isInitialized by rememberSaveable { mutableStateOf(false) }

    val cities by viewModel.cityList.collectAsState()
    val isCityListLoading by viewModel.isCityListLoading.collectAsState()

    val city by viewModel.city.collectAsState(initial = null)

    if (!isInitialized) {
        LaunchedEffect(Unit) {
            viewModel.getCitiesList()
            isInitialized = true
        }
    }

    if (city != null) {
        navController.navigate("city/${city?.id ?: -1}") {
            popUpTo("welcome") {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (isCityListLoading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Loading a list of cities"
                )
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            WelcomeContent(
                listCities = cities,
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

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CharacterFilterDialogPreview() {
    WeatherTheme {
        WelcomeScreen()
    }
}