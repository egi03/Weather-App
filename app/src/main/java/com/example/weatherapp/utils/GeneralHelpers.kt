package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object GeneralHelpers {
    fun formatTimeFromUnix(unixTime: Number): String {
        return try{
            val timeInMillis = unixTime.toLong() * 1000L
            val date = Date(timeInMillis)
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            dateFormat.format(date)
        } catch (e: Exception){
            "--:--"
        }
    }

    fun formatDateTimeFromUnix(unixTime: Number): String {
        return try {
            val timeInMillis = unixTime.toLong() * 1000L
            val date = Date(timeInMillis)
            val dateFormat = SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault())
            dateFormat.format(date)
        } catch (e: Exception) {
            "--:--"
        }
    }

    fun formatDate(date: String): String {
        return try{
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateObj = dateFormat.parse(date)
            val newDateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
            newDateFormat.format(dateObj!!)
        } catch (e: Exception){
            "--"
        }
    }

    fun String.capitalizeWords(delimiter: String = " "): String {
        return split(delimiter).joinToString(delimiter) { word ->
            val smallCaseWord = word.lowercase()
            smallCaseWord.replaceFirstChar(Char::titlecaseChar)
        }
    }
}