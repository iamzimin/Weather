package com.evg.weather_api.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository
import com.evg.weather_api.domain.mapper.toCityDBO
import com.evg.weather_api.domain.mapper.toCurrentWeatherDBO
import com.evg.weather_api.domain.mapper.toWeeklyForecastDBO
import com.evg.weather_api.domain.models.CityResponse
import com.evg.weather_api.domain.models.CurrentWeatherResponse
import com.evg.weather_api.domain.models.WeeklyForecastResponse
import com.evg.weather_api.domain.repository.WeatherApiRepository
import com.evg.weather_api.domain.service.CityApi
import com.evg.weather_api.domain.service.WeatherApi
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
    retrofitCity: Retrofit,
    retrofitWeather: Retrofit,
    private val databaseRepository: DatabaseRepository,
    private val sharedPrefsRepository: SharedPrefsRepository,
): WeatherApiRepository {
    private val cityApi = retrofitCity.create(CityApi::class.java)
    private val weatherApi = retrofitWeather.create(WeatherApi::class.java)
    private val weatherApiKey = "077498dc11566a8e3a76031ffef73fef"

    override suspend fun downloadCitiesFile(): List<CityResponse>? {
        val url = "city.list.min.json.gz" // old current.city.list.min.json.gz

        try {
            if (!sharedPrefsRepository.getIsCitiesListDownloaded()) {
                val response = cityApi.downloadFile(url)

                if (response.isSuccessful) {
                    val responseBody = response.body() ?: return null

                    val file = File(context.cacheDir, url)

                    withContext(Dispatchers.IO) {
                        val fos = FileOutputStream(file)
                        fos.write(responseBody.bytes())
                        fos.close()
                    }

                    sharedPrefsRepository.saveIsCitiesListDownloaded()

                    return saveResponseToDB(tarGzFile = file)
                }
            } else {
                val file = File(context.cacheDir, url)

                return saveResponseToDB(tarGzFile = file)
            }
        } catch (e: Exception) {
            return null
        }

        return null
    }

    override suspend fun getCurrentWeather(cityId: Int): CurrentWeatherResponse? {
        return try {
            val weather = weatherApi.getCurrentWeather(
                cityId = cityId,
                apiKey = weatherApiKey,
            )

            val weatherDBO = weather?.toCurrentWeatherDBO()
            weatherDBO?.let {
                databaseRepository.insertCurrentWeather(
                    currentWeather = it
                )
            }

            return weather
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getForecastWeeklyWeather(cityId: Int): WeeklyForecastResponse? {
        return try {
            //throw Exception("dsfsd")
            val weather = weatherApi.getWeeklyForecast(
                cityId = cityId,
                apiKey = weatherApiKey,
            )

            val weatherDBO = weather?.toWeeklyForecastDBO()
            weatherDBO?.let {
                databaseRepository.insertWeeklyForecast(
                    weeklyForecast = it
                )
            }

            return weather
        } catch (e: Exception) {
            null
        }
    }

    override fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }


    private suspend fun saveResponseToDB(tarGzFile: File): List<CityResponse>  {
        val jsonFile = File(context.cacheDir, "currentCityList.json")

        withContext(Dispatchers.IO) {
            GZIPInputStream(FileInputStream(tarGzFile)).use { gzInputStream ->
                FileOutputStream(jsonFile).use { out ->
                    gzInputStream.copyTo(out)
                }
            }
        }

        val jsonString = jsonFile.readText()
        val listType = object : TypeToken<List<CityResponse>>() {}.type
        val cities: List<CityResponse> = Gson().fromJson(jsonString, listType)

        databaseRepository.insertCities(cities.map { it.toCityDBO() })

        tarGzFile.delete()
        jsonFile.delete()

        sharedPrefsRepository.saveIsCitiesListUnzipped()

        return cities
    }

}