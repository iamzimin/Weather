package com.evg.weather_city.domain.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

internal fun Double.kelvinToCelsius(): Int {
    return (this - 273.15).roundToInt()
}

internal fun Long.timestampFormatToString(pattern: String, timezone: Int): String {
    val value = if (this < 0) 0 else this

    val instant = Instant.ofEpochSecond(value)
    val zoneOffset = ZoneOffset.ofTotalSeconds(timezone)
    val localDateTime = LocalDateTime.ofInstant(instant, zoneOffset)

    val formatter = DateTimeFormatter.ofPattern(pattern)
    return localDateTime.format(formatter)
}

internal fun convertTimestampToDayOfWeek(date: Long, timezoneOffsetSeconds: Int): String {
    val currentDate = ZonedDateTime.now(ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds)).toLocalDate()
    val givenDate = ZonedDateTime.ofInstant(Instant.ofEpochSecond(date), ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds)).toLocalDate()

    return when {
        givenDate.isEqual(currentDate) -> "Today"
        givenDate.isEqual(currentDate.plusDays(1)) -> "Tomorrow"
        else -> givenDate.format(DateTimeFormatter.ofPattern("EEEE"))
    }
}

internal fun isStartOfNewDay(previousDt: Long, currentDt: Long, timezoneOffsetSeconds: Int): Boolean {
    val previousDay = ZonedDateTime.ofInstant(Instant.ofEpochSecond(previousDt), ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds)).toLocalDate()
    val currentDay = ZonedDateTime.ofInstant(Instant.ofEpochSecond(currentDt), ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds)).toLocalDate()
    return previousDay != currentDay
}

