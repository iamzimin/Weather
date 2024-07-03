package com.evg.shared_prefs.data.repository

import android.content.Context
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository

class SharedPrefsRepositoryImpl(
    context: Context,
): SharedPrefsRepository {
    private val sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)

    override fun saveLatestCity(id: Int) {
        with(sharedPreferences.edit()) {
            putInt("latestCity", id)
            apply()
        }
    }

    override fun getLatestCity(): Int? {
        val latestCity = sharedPreferences.getInt("latestCity", -1)
        return if (latestCity != -1) {
            latestCity
        } else {
            null
        }
    }

    override fun saveIsCitiesListDownloaded() {
        with(sharedPreferences.edit()) {
            putBoolean("isCitiesListDownloaded", true)
            apply()
        }
    }

    override fun getIsCitiesListDownloaded(): Boolean {
        return sharedPreferences.getBoolean("isCitiesListDownloaded", false)
    }

    override fun saveIsCitiesListUnzipped() {
        with(sharedPreferences.edit()) {
            putBoolean("isCitiesListUnzipped", true)
            apply()
        }
    }

    override fun getIsCitiesListUnzipped(): Boolean {
        return sharedPreferences.getBoolean("isCitiesListUnzipped", false)
    }
}