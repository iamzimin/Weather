package com.evg.selection_city.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.ExposedCityMenu
import com.evg.resource.LocalNavHostController
import com.evg.resource.R
import com.evg.resource.theme.WeatherTheme
import com.evg.selection_city.domain.model.City
import com.evg.selection_city.domain.model.CityInfo
import com.evg.selection_city.presentation.mapper.toCity
import com.evg.selection_city.presentation.mapper.toCityInfoUI
import com.evg.selection_city.presentation.mapper.toCityUI

@Composable
fun SelectionCityContent(
    cityInfo: List<CityInfo>,
    listCities: List<City>?,
    currentCityId: Int,
    deleteCity: (Int) -> Unit,
    setCityString: (String?) -> Unit,
    setCity: (City?) -> Unit,
    onCityApply: () -> Unit,
) {
    val navController = LocalNavHostController.current
    var isDeleteMode: Boolean by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (isDeleteMode) {
                            isDeleteMode = false
                        } else {
                            navController.popBackStack()
                        }
                    }
                ) {
                    if (isDeleteMode) {
                        Icon(
                            modifier = Modifier.padding(5.dp),
                            imageVector = Icons.Default.Close,
                            tint = MaterialTheme.colorScheme.inverseSurface,
                            contentDescription = "Arrow back",
                        )
                    } else {
                        Icon(
                            modifier = Modifier.padding(5.dp),
                            painter = painterResource(id = R.drawable.arrow_back),
                            tint = MaterialTheme.colorScheme.inverseSurface,
                            contentDescription = "Arrow back",
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Manage cities"
                )
            }

            ExposedCityMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 10.dp
                    ),
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                items(
                    count = cityInfo.size,
                ) { index ->
                    CityInfoTile(
                        cityInfo = cityInfo[index].toCityInfoUI(),
                        currentCityId = currentCityId,
                        deleteCity = {
                            deleteCity(it)
                        },
                        isDeleteMode = isDeleteMode,
                    )
                }
            }

            if (!isDeleteMode) {
                Row(
                    modifier = Modifier
                        //.align(Alignment.BottomCenter)
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(top = 5.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onCityApply()
                        }
                    ) {
                        Text(text = "Add city")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            isDeleteMode = !isDeleteMode
                        }
                    ) {
                        Text(text = "Remove city")
                    }
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SelectionCityContentPreview() {
    WeatherTheme {
        SelectionCityContent(
            cityInfo = List(0) {
                CityInfo(
                    id = it,
                    city = "Paris",
                    skyDescription = "Sunny",
                    temp = 205.4,
                    tempMax = 211.4,
                    tempMin = 200.4,
                )
            },
            listCities = emptyList(),
            currentCityId = -1,
            deleteCity = { },
            setCityString = { },
            setCity = { },
            onCityApply = { },
        )
    }
}