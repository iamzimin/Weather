package com.evg.selection_city.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.WeatherTheme
import com.evg.selection_city.presentation.model.CityInfoUI

@Composable
fun CityInfoTile(
    cityInfo: CityInfoUI,
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(BorderRadius))
            .background(color = Color.Gray)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(

            ) {
                Text(
                    text = cityInfo.city,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 40.sp,
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = cityInfo.temp.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 40.sp,
                )
            }
            Row {
                Text(
                    text = cityInfo.skyDescription
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "${cityInfo.tempMax}/${cityInfo.tempMin}"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CityInfoTilePreview() {
    WeatherTheme {
        CityInfoTile(
            cityInfo = CityInfoUI(
                id = 1,
                city = "Paris",
                skyDescription = "Sunny",
                temp = 17,
                tempMax = 20,
                tempMin = 15,
            )
        )
    }
}