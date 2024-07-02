package com.evg.weather_city.domain.mapper

import com.evg.weather_api.domain.models.WeeklyForecastResponse
import com.evg.weather_city.domain.model.WeeklyForecast

internal fun WeeklyForecastResponse.toWeeklyForecast(): WeeklyForecast {
    return WeeklyForecast(
        cod = this.cod,
    )
}