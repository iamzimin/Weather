package com.evg.weather_city.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.evg.resource.theme.WeatherTheme
import com.evg.weather_city.domain.utils.convertTimestampToDayOfWeek
import com.evg.weather_city.domain.utils.timestampFormatToString
import com.evg.weather_city.presentation.model.DailyForecastUI

@Composable
fun DailyRowTile(
    dailyForecastUI: DailyForecastUI,
    timezone: Int,
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = convertTimestampToDayOfWeek(
                date = dailyForecastUI.timestamp,
                timezoneOffsetSeconds = timezone,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )

        Text(
            text = dailyForecastUI.timestamp.timestampFormatToString(
                pattern = "dd/MM",
                timezone = timezone,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )


        SubcomposeAsyncImage(
            model = dailyForecastUI.getIconUrl(),
            modifier = Modifier.size(30.dp),
            contentDescription = dailyForecastUI.getIconUrl(),
            alignment = Alignment.CenterStart,
            contentScale = ContentScale.FillBounds,
            loading = {

            },
            error = {

            },
        )

        Text(
            text = dailyForecastUI.weatherDescription,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = "${dailyForecastUI.tempMax}/${dailyForecastUI.tempMin}°C",
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DailyRowTilePreview() {
    WeatherTheme {
        DailyRowTile(
            dailyForecastUI = DailyForecastUI(
                timestamp = 1720066619,
                icon = "",
                weatherDescription = "Cloud",
                tempMax = 20,
                tempMin = 14,
            ),
            timezone = 0,
        )
    }
}