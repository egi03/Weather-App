package com.example.weatherapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastItem>,
    val city: City
) : Parcelable

@Parcelize
data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val sys: ForecastSys,
    val dt_txt: String
) : Parcelable

@Parcelize
data class ForecastSys(
    val pod: String
) : Parcelable

@Parcelize
data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
) : Parcelable