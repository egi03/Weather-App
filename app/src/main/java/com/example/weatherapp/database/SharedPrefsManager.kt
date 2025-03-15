package com.example.weatherapp.database

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.WeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("WeatherAppPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveLocation(name: String, weatherData: WeatherResponse, forecastData: ForecastResponse) {
        val locations = getLocations().toMutableList()
        if (!locations.contains(name)) {
            locations.add(0, name)
            if (locations.size > 5) {
                locations.removeAt(5)
            }
        }
        else{
            locations.remove(name)
            locations.add(0, name)
        }

        prefs.edit()
            .putString("locations", gson.toJson(locations))
            .putString("date_$name", weatherData.dt.toString())
            .putString("weather_$name", gson.toJson(weatherData))
            .putString("forecast_$name", gson.toJson(forecastData))
            .apply()
    }

    fun getLocations(): List<String> {
        val locationsJson = prefs.getString("locations", null) ?: return listOf()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(locationsJson, type)
    }

    fun getWeather(name: String): WeatherResponse?{
        val json = prefs.getString("weather_$name", null) ?: return null
        return gson.fromJson(json, WeatherResponse::class.java)
    }

    fun getForecast(name: String): ForecastResponse?{
        val json = prefs.getString("forecast_$name", null) ?: return null
        return gson.fromJson(json, ForecastResponse::class.java)
    }

    fun getDateTime(name: String): String{
        return prefs.getString("date_$name", null) ?: "Unknown date"
    }

}