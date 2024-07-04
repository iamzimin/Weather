package com.evg.weather_city.presentation

import android.content.res.Configuration
import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evg.resource.R
import com.evg.resource.theme.WeatherTheme
import com.evg.weather_city.presentation.model.CurrentWeatherCloudsUI
import com.evg.weather_city.presentation.model.CurrentWeatherInfoUI
import com.evg.weather_city.presentation.model.CurrentWeatherMainUI
import com.evg.weather_city.presentation.model.CurrentWeatherSysUI
import com.evg.weather_city.presentation.model.CurrentWeatherUI
import com.evg.weather_city.presentation.model.CurrentWeatherWindUI

@Composable
fun DetailedInfoTile(
    modifier: Modifier,
    icon: Painter? = null,
    title: String? = null,
    info: String? = null,
    unit: String = "",
) {
    Column(
        modifier = modifier
            .height(120.dp)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.background,
                shape = RectangleShape
            )
            .padding(vertical = 20.dp, horizontal = 5.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
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
                .fillMaxWidth()
                .padding(top = 5.dp),
            text = title?: "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 8.sp,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = if (info != null) "$info$unit" else "",
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 13.sp,
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailedInfoTilePreview() {
    WeatherTheme {
        DetailedInfoTile(
            modifier = Modifier,
            icon = painterResource(id = R.drawable.location),
            title = "Title",
            info = "50",
            unit = "%"
        )
    }
}