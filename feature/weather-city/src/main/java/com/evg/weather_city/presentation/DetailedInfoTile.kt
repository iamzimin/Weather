package com.evg.weather_city.presentation

import android.content.res.Configuration
import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.resource.theme.WeatherTheme
import com.evg.weather_city.presentation.model.CurrentWeatherCloudsUI
import com.evg.weather_city.presentation.model.CurrentWeatherCoordinatesUI
import com.evg.weather_city.presentation.model.CurrentWeatherInfoUI
import com.evg.weather_city.presentation.model.CurrentWeatherMainUI
import com.evg.weather_city.presentation.model.CurrentWeatherRainUI
import com.evg.weather_city.presentation.model.CurrentWeatherSnowUI
import com.evg.weather_city.presentation.model.CurrentWeatherSysUI
import com.evg.weather_city.presentation.model.CurrentWeatherUI
import com.evg.weather_city.presentation.model.CurrentWeatherWindUI

@Composable
fun DetailedInfoTile(
    modifier: Modifier,
    icon: Painter? = null,
    title: String? = null,
    info: String? = null,
) {
    Column(
        modifier = modifier
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RectangleShape
            )
            .padding(vertical = 20.dp, horizontal = 10.dp),
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .align(alignment = Alignment.CenterHorizontally)
                .size(25.dp),
        ) {
            icon?.let {
                Icon(
                    painter = icon,
                    tint = MaterialTheme.colorScheme.inverseSurface,
                    contentDescription = "Filter",
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = title?: "",
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = info?: "",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailedInfoTilePreview() {
    WeatherTheme {
        DetailedInfoTile(
            modifier = Modifier,
            icon = painterResource(id = R.drawable.location),
            title = "Title",
            info = "50%",
        )
    }
}