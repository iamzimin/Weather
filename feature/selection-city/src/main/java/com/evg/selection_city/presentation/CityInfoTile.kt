package com.evg.selection_city.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.LocalNavHostController
import com.evg.resource.ShimmerImage
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.WeatherTheme
import com.evg.selection_city.presentation.model.CityInfoUI

@Composable
fun CityInfoTile(
    cityInfo: CityInfoUI,
    currentCityId: Int,
    deleteCity: (Int) -> Unit,
    isDeleteMode: Boolean,
) {
    val navController = LocalNavHostController.current

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(BorderRadius))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                if (!isDeleteMode) {
                    navController.navigate("city/${cityInfo.id}")
                }
            }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                ) {
                    Text(
                        text = cityInfo.city,
                        style = MaterialTheme.typography.headlineLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = cityInfo.skyDescription,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                if (isDeleteMode) {
                    if (currentCityId != cityInfo.id) {
                        IconButton(
                            modifier = Modifier
                                .size(70.dp),
                            onClick = {
                                deleteCity(cityInfo.id)
                            }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(40.dp),
                                imageVector = Icons.Default.Delete,
                                tint = MaterialTheme.colorScheme.inverseSurface,
                                contentDescription = "Arrow back",
                            )
                        }
                    }
                } else {
                    ShimmerImage(
                        url = cityInfo.getIconUrl(),
                        size = 30,
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "${cityInfo.temp}°C",
                        style = MaterialTheme.typography.headlineLarge,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CityInfoTileDeleteModePreview() {
    WeatherTheme {
        val cityInfo = CityInfoUI(
            id = 1,
            city = "Paris",
            skyDescription = "Sunny",
            weatherIcon = "",
            temp = 17,
            tempMax = 20,
            tempMin = 15,
        )
        Column {
            CityInfoTile(
                cityInfo = cityInfo,
                currentCityId = 2,
                deleteCity = { },
                isDeleteMode = true,
            )
            CityInfoTile(
                cityInfo = cityInfo,
                currentCityId = 2,
                deleteCity = { },
                isDeleteMode = false,
            )
        }
    }
}