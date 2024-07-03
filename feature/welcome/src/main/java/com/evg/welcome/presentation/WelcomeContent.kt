package com.evg.welcome.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evg.resource.ExposedCityMenu
import com.evg.resource.theme.WeatherTheme
import com.evg.welcome.domain.model.City
import com.evg.welcome.presentation.mapper.toCity
import com.evg.welcome.presentation.mapper.toCityUI

@Composable
fun WelcomeContent(
    listCities: List<City>?,
    setCityString: (String?) -> Unit,
    setCity: (City?) -> Unit,
    onCityApply: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 20.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 50.dp),
            text = "Weather",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 40.sp,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
            text = "Choose your city",
            style = MaterialTheme.typography.bodyMedium,
        )

        ExposedCityMenu(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            listCities = listCities?.map { it.toCityUI() },
            onSelect = { cityUI ->
                setCityString(null)
                setCity(cityUI.toCity())
            },
            onEdit = { text ->
                setCity(null)
                setCityString(text)
            }
        )

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = {
                onCityApply()
            }
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Submit"
            )
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun WelcomeContentPreview() {
    WeatherTheme {
        WelcomeContent(
            listCities = listOf(
                City(
                    id = 1,
                    coordLat = 123.568,
                    coordLon = 2356.970,
                    name = "Moscow"
                ),
                City(
                    id = 2,
                    coordLat = 45.845,
                    coordLon = 456.00,
                    name = "Samara"
                ),
                City(
                    id = 33,
                    coordLat = 345.898,
                    coordLon = 46756.23,
                    name = "Ulyanovsk"
                ),
            ),
            setCityString = { },
            setCity = { },
            onCityApply = { }
        )
    }
}