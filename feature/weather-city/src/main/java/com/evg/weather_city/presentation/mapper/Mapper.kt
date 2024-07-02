package com.evg.weather_city.presentation.mapper

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


internal fun Double.kelvinToCelsius(): Int {
    return (this - 273.15).roundToInt()
}

fun Long.timestampFormatToString(pattern: String, timezone: Int): String {
    val value = if (this < 0) 0 else this

    val instant = Instant.ofEpochSecond(value)
    val zoneOffset = ZoneOffset.ofTotalSeconds(timezone)
    val localDateTime = LocalDateTime.ofInstant(instant, zoneOffset)

    val formatter = DateTimeFormatter.ofPattern(pattern)
    return localDateTime.format(formatter)
    /*val value = if (this < 0) 0 else this

    val instant = Instant.ofEpochSecond(value)

    val zoneId = ZoneId.systemDefault()
    val localTime = ZonedDateTime.ofInstant(instant, zoneId)
    val formatter = DateTimeFormatter.ofPattern(pattern)

    return localTime.format(formatter)*/
}