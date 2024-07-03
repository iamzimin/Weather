package com.evg.selection_city.presentation

import android.content.res.Configuration
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.ExposedCityMenu
import com.evg.resource.R
import com.evg.resource.theme.WeatherTheme
import com.evg.selection_city.domain.model.City
import com.evg.selection_city.domain.model.CityInfo
import com.evg.selection_city.presentation.mapper.toCityInfoUI
import com.evg.selection_city.presentation.mapper.toCityUI
import com.evg.selection_city.presentation.model.CityInfoUI

@Composable
fun SelectionCityContent(
    cityInfo: List<CityInfo>,
    listCities: List<City>?,
) {
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
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        tint = MaterialTheme.colorScheme.inverseSurface,
                        contentDescription = "Filter",
                    )
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
                        top = 20.dp,
                        bottom = 10.dp
                    ),
                listCities = listCities?.map { it.toCityUI() },
                onSelect = { cityUI ->
                    //typedCityText = null
                    //setCity(city.toCity())
                },
                onEdit = { text ->
                    //typedCityText = text
                }
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                items(
                    count = cityInfo.size,
                ) { index ->
                    CityInfoTile(
                        cityInfo = cityInfo[index].toCityInfoUI(),
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .padding(top = 15.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Add city")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Remove city")
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
            cityInfo = List(4) {
                CityInfo(
                    id = it,
                    city = "Paris",
                    skyDescription = "Sunny",
                    temp = 17.5,
                    tempMax = 20.5,
                    tempMin = 15.2,
                )
            },
            listCities = emptyList()
        )
    }
}