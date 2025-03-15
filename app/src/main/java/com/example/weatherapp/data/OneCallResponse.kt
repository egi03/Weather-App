package com.example.weatherapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OneCallResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val current: Current,
    val hourly: List<HourlyForecast>,
    val daily: List<DailyForecast>
) : Parcelable

@Parcelize
data class Current(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val weather: List<Weather>
) : Parcelable

@Parcelize
data class HourlyForecast(
    val dt: Long,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val wind_gust: Double,
    val weather: List<Weather>,
    val pop: Double
) : Parcelable

@Parcelize
data class DailyForecast(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moon_phase: Double,
    val temp: DailyTemp,
    val feels_like: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val wind_speed: Double,
    val wind_deg: Int,
    val wind_gust: Double,
    val weather: List<Weather>,
    val clouds: Int,
    val pop: Double,
    val uvi: Double,
    val rain: Double? = null
) : Parcelable

@Parcelize
data class DailyTemp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable

@Parcelize
data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) : Parcelable