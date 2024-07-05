package com.evg.weather_city.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.resource.ShimmerImage
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.Pink80
import com.evg.resource.theme.WeatherTheme
import com.evg.weather_city.domain.utils.timestampFormatToString
import com.evg.weather_city.presentation.model.HourlyForecastUI

@Composable
fun HourlyRowTile(
    hourlyForecastUI: HourlyForecastUI,
    timezone: Int,
) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .height(110.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = hourlyForecastUI.timestamp.timestampFormatToString(
                pattern = "HH:mm",
                timezone = timezone,
            ),
            textAlign = TextAlign.Center,
        )

        ShimmerImage(
            url = hourlyForecastUI.getIconUrl(),
            size = 30,
        )

        Text(
            text = "${hourlyForecastUI.temp}°C",
            textAlign = TextAlign.Center,
        )

    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HourlyRowTilePreview() {
    WeatherTheme {
        HourlyRowTile(
            hourlyForecastUI = HourlyForecastUI(
                timestamp = 1719942107,
                temp = 24,
                icon = "",
            ),
            timezone = 0,
        )
    }
}