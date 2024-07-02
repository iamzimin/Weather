package com.evg.weather_api.di

import android.content.Context
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.weather_api.data.repository.WeatherApiRepositoryImpl
import com.evg.weather_api.domain.repository.WeatherApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {

    @Named("City")
    @Provides
    @Singleton
    fun provideCityRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://bulk.openweathermap.org/sample/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Named("Weather")
    @Provides
    @Singleton
    fun provideWeatherRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    @Provides
    @Singleton
    fun provideWeatherApiRepository(
        @ApplicationContext context: Context,
        @Named("City") retrofitCity: Retrofit,
        @Named("Weather") retrofitWeather: Retrofit,
        databaseRepository: DatabaseRepository
    ): WeatherApiRepository {
        println("provided WeatherApiRepositoryImpl")
        return WeatherApiRepositoryImpl(
            context = context,
            retrofitCity = retrofitCity,
            retrofitWeather = retrofitWeather,
            databaseRepository = databaseRepository,
        )
    }
}