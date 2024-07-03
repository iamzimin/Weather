package com.evg.selection_city.di

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.selection_city.data.repository.SelectionCityRepositoryImpl
import com.evg.selection_city.domain.repository.SelectionCityRepository
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SelectionCityModule {
    @Provides
    @Singleton
    fun provideWeatherCityRepository(
        databaseRepository: DatabaseRepository,
        sharedPrefsRepository: SharedPrefsRepository,
    ): SelectionCityRepository {
        println("provided SelectionCityRepository")
        return SelectionCityRepositoryImpl(
            databaseRepository = databaseRepository,
            sharedPrefsRepository = sharedPrefsRepository,
        )
    }
}