package com.example.weatherapp.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.OneCallResponse
import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.data.WeatherResponse
import com.example.weatherapp.database.SharedPrefsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WelcomeScreenViewModel(application: Application): AndroidViewModel(application) {
    private val sharedPrefsManager = SharedPrefsManager(getApplication())
    private val _previousSearches = MutableStateFlow<List<String>>(emptyList())
    val previousSearches: StateFlow<List<String>> = _previousSearches

    init{
        loadSavedLocations()
    }

    private fun loadSavedLocations(){
        _previousSearches.value = sharedPrefsManager.getLocations()
    }

    private val apiKey = "api_key_here"
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build())
        .build()

    private val weatherApi = retrofit.create(WeatherApi::class.java)

    suspend fun getDataFromAPI(location: String): WeatherResponse? {
        return try {
            val response = weatherApi.getWeatherData(
                city = location,
                apiKey = apiKey
            )
            println("API Response: $response")
            response
        } catch (e: Exception) {
            println("API Error: ${e.message}")
            e.printStackTrace()
            null
        }
    }

    suspend fun getForecastData(location: String): ForecastResponse? {
        return try {
            val response = weatherApi.getForecastData(
                city = location,
                apiKey = apiKey
            )
            response
        }catch (e:Exception){
            println("Forecalst API error: ${e.message}")
            e.printStackTrace()
            null
        }
    }

    suspend fun getDetailedForecast(lat: Double, lon: Double): OneCallResponse? {
        return try{
            val response = weatherApi.getOneCallData(
                lat = lat,
                lon = lon,
                apiKey = apiKey
            )
            response
        } catch (e: Exception){
            println("OneCall API error: ${e.message}")
            e.printStackTrace()
            null
        }
    }

    suspend fun getAllWeatherData(location: String): Pair<WeatherResponse?, ForecastResponse?> {
        val weatherData = getDataFromAPI(location)
        val forecastData = getForecastData(location)

        if(weatherData != null && forecastData != null){
            sharedPrefsManager.saveLocation(location, weatherData, forecastData)
            _previousSearches.value = sharedPrefsManager.getLocations()
        }

        return Pair(weatherData, forecastData)
    }
}