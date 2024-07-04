package com.evg.weather_city.presentation

import android.content.res.Configuration
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evg.resource.LocalNavHostController
import com.evg.resource.R
import com.evg.resource.theme.BorderRadius
import com.evg.resource.theme.WeatherTheme
import com.evg.weather_city.domain.utils.timestampFormatToString
import com.evg.weather_city.presentation.model.CurrentWeatherCloudsUI
import com.evg.weather_city.presentation.model.CurrentWeatherInfoUI
import com.evg.weather_city.presentation.model.CurrentWeatherMainUI
import com.evg.weather_city.presentation.model.CurrentWeatherSysUI
import com.evg.weather_city.presentation.model.CurrentWeatherUI
import com.evg.weather_city.presentation.model.CurrentWeatherWindUI
import com.evg.weather_city.presentation.model.DailyForecastUI
import com.evg.weather_city.presentation.model.HourlyForecastUI
import kotlinx.coroutines.delay
import java.util.TimeZone

@Composable
fun WeatherContent(
    currentWeather: CurrentWeatherUI,
    hourlyWeather: List<HourlyForecastUI>,
    dailyWeather: List<DailyForecastUI>,
) {
    val navController = LocalNavHostController.current
    val horizontalPadding = 30.dp
    val scrollState = rememberScrollState()
    var isUpdateTextVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        isUpdateTextVisible = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val updateTime = currentWeather.timestamp.timestampFormatToString(
                        pattern = "HH:mm",
                        timezone = TimeZone.getDefault().rawOffset / 1000,
                    )
                    androidx.compose.animation.AnimatedVisibility(
                        visible = isUpdateTextVisible,
                        enter = slideInVertically(initialOffsetY = { -40 }) + fadeIn(),
                        exit = slideOutVertically(targetOffsetY = { -40 }) + fadeOut()
                    ) {
                        Text(
                            text = "Updated: $updateTime",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(35.dp),
                        onClick = {
                            navController.navigate("cityList")
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            tint = MaterialTheme.colorScheme.inverseSurface,
                            contentDescription = "Filter",
                        )
                    }
                }


                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(
                            top = 20.dp,
                            bottom = 30.dp,
                            start = horizontalPadding,
                            end = horizontalPadding,
                        )
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentWeather.name,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = currentWeather.main.temp.toString(),
                            fontSize = 100.sp,
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 15.dp),
                            text = "°C",
                            fontSize = 25.sp,
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "${currentWeather.main.tempMax}/${currentWeather.main.tempMin}°C",
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentWeather.weather[0].main,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(BorderRadius))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            LazyRow {
                items(
                    count = hourlyWeather.size,
                ) { index ->
                    HourlyRowTile(
                        hourlyForecastUI = hourlyWeather[index],
                        timezone = currentWeather.timezone,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(BorderRadius))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            LazyRow {
                items(
                    count = dailyWeather.size,
                ) { index ->
                    DailyRowTile(
                        dailyForecastUI = dailyWeather[index],
                        timezone = currentWeather.timezone,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "More detailed",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(3.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(BorderRadius))
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Row {
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.thermometer),
                    title = "Feels like",
                    info = currentWeather.main.feelsLike.toString(),
                    unit = "°C",
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.pressure),
                    title = "Pressure",
                    info = currentWeather.main.pressure.toString(),
                    unit = "hPa",
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.humidity),
                    title = "Humidity",
                    info = currentWeather.main.humidity.toString(),
                    unit = "%",
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.mountain),
                    title = "Altitude",
                    info = currentWeather.main.seaLevel.toString(),
                    unit = "m",
                )
            }
            Row {
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.wind_speed),
                    title = "Wind speed",
                    info = currentWeather.wind.speed.toString(),
                    unit = "m/s",
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.wind_direction),
                    title = "Wind Direction",
                    info = currentWeather.wind.deg.toString(),
                    unit = "°",
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.cloud),
                    title = "Cloudiness",
                    info = currentWeather.clouds.cloudiness.toString(),
                    unit = "%",
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.visibility),
                    title = "Visibility",
                    info = currentWeather.visibility.toString(),
                    unit = "m",
                )
            }
            Row {
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.sunrise),
                    title = "Sunrise",
                    info = currentWeather.sys.sunrise.timestampFormatToString(
                        pattern = "HH:mm",
                        timezone = currentWeather.timezone,
                    ),
                    unit = "",
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                    icon = painterResource(id = R.drawable.sunset),
                    title = "Sunset",
                    info = currentWeather.sys.sunset.timestampFormatToString(
                        pattern = "HH:mm",
                        timezone = currentWeather.timezone,
                    ),
                    unit = "",
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                )
                DetailedInfoTile(
                    modifier = Modifier.weight(1f),
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun WeatherContentPreview() {
    WeatherTheme {
        WeatherContent(
            currentWeather = CurrentWeatherUI(
                //coordinates = CurrentWeatherCoordinatesUI(lon = 34.56, lat = -78.9),
                weather = listOf(
                    CurrentWeatherInfoUI(id = 800, main = "Clear", description = "clear sky", icon = "01d")
                ),
                main = CurrentWeatherMainUI(
                    temp = 22, feelsLike = 25, tempMin = 20, tempMax = 25,
                    pressure = 1010, humidity = 70, seaLevel = 1015, groundLevel = 1005
                ),
                visibility = 10000,
                wind = CurrentWeatherWindUI(speed = 3.5, deg = 180, gust = 4.2),
                //rain = CurrentWeatherRainUI(oneHour = 0.0, threeHour = 0.0),
                //snow = CurrentWeatherSnowUI(oneHour = 0.0, threeHour = 0.0),
                clouds = CurrentWeatherCloudsUI(cloudiness = 10),
                timestamp = System.currentTimeMillis() / 1000,
                sys = CurrentWeatherSysUI(country = "US", sunrise = 1625222257, sunset = 1625273893),
                timezone = -14400,
                id = 5128581,
                name = "New York"
            ),
            hourlyWeather = List(8) {
                HourlyForecastUI(
                    timestamp = 1719942107,
                    temp = 24,
                    icon = "",
                )
            },
            dailyWeather = List(5) {
                DailyForecastUI(
                    timestamp = 1719942107,
                    icon = "",
                    weatherDescription = "Cloud",
                    tempMax = 20,
                    tempMin = 14,
                )
            },
        )
    }
}