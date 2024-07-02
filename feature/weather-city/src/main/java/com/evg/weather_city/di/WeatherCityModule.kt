package com.evg.weather_city.di

import com.evg.weather_api.domain.repository.WeatherApiRepository
import com.evg.weather_city.data.repository.WeatherCityRepositoryImpl
import com.evg.weather_city.domain.repository.WeatherCityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherCityModule {
    @Provides
    @Singleton
    fun provideWeatherCityRepository(
        apiRepository: WeatherApiRepository
    ): WeatherCityRepository {
        println("provided provideWeatherCityRepository")
        return WeatherCityRepositoryImpl(
            apiRepository = apiRepository,
        )
    }
}