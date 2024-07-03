package com.evg.shared_prefs.domain.repository

interface SharedPrefsRepository {
    fun saveLatestCity(id: Int)
    fun getLatestCity(): Int?

    fun saveIsCitiesListDownloaded()
    fun getIsCitiesListDownloaded(): Boolean

    fun saveIsCitiesListUnzipped()
    fun getIsCitiesListUnzipped(): Boolean
}