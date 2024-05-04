package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class CyclingDataModel(

    val name: String,
    val distance: String,
    val movingTime: String,
    val type: String,
    val avgSpeed: String,
    val startDate: String,
    val maxSpeed: String,
    val locationCountry: String,
    val avgTemp: String,
    val hasHeartRate: String,
    val avgHeartRate: String,
    val maxHeartRate: String,
    val elevGain: String,
    val elevHigh: String,
    val elevLow: String,

)