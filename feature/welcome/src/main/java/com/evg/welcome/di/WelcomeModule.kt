package com.evg.welcome.di

import android.content.Context
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.weather_api.domain.repository.WeatherApiRepository
import com.evg.welcome.data.repository.WelcomeRepositoryImpl
import com.evg.welcome.domain.repository.WelcomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WelcomeModule {

    @Provides
    @Singleton
    fun provideWelcomeRepository(
        @ApplicationContext context: Context,
        weatherApiRepository: WeatherApiRepository,
        databaseRepository: DatabaseRepository,
    ): WelcomeRepository {
        println("provided WelcomeRepositoryImpl")
        return WelcomeRepositoryImpl(
            context = context,
            weatherApiRepository = weatherApiRepository,
            databaseRepository = databaseRepository,
        )
    }
}