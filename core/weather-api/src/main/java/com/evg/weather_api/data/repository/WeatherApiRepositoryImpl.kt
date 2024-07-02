package com.evg.weather_api.data.repository

import android.content.Context
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.weather_api.domain.mapper.toCityDBO
import com.evg.weather_api.domain.models.CityResponse
import com.evg.weather_api.domain.repository.WeatherApiRepository
import com.evg.weather_api.domain.service.WeatherCityApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.GZIPInputStream

class WeatherApiRepositoryImpl(
    private val context: Context,
    private val retrofitCity: Retrofit,
    private val databaseRepository: DatabaseRepository,
): WeatherApiRepository {
    override suspend fun downloadCityFile(): List<CityResponse>? {
        val api = retrofitCity.create(WeatherCityApi::class.java)

        val response = api.downloadFile("sample/current.city.list.min.json.gz")
        return withContext(Dispatchers.IO) {
            if (response.isSuccessful) {
                val responseBody = response.body() ?: return@withContext null

                val file = File(context.cacheDir, "current.city.list.min.json.tar.gz")
                val fos = FileOutputStream(file)
                fos.write(responseBody.bytes())
                fos.close()

                // Распаковка gz файла
                val jsonFile = File(context.cacheDir, "currentCityList.json")

                GZIPInputStream(FileInputStream(file)).use { gzInputStream ->
                    FileOutputStream(jsonFile).use { out ->
                        gzInputStream.copyTo(out)
                    }
                }

                // Чтение JSON файла
                val jsonString = jsonFile.readText()
                val listType = object : TypeToken<List<CityResponse>>() {}.type
                val cities: List<CityResponse> = Gson().fromJson(jsonString, listType)

                // Cохранение в бд
                databaseRepository.insertCities(cities.map { it.toCityDBO() })

                val editor = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE).edit()
                editor.putBoolean("isCityDownloaded", true)
                editor.apply()

                return@withContext cities
            }

            return@withContext null
        }
    }
}