package com.example.myapplication.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cyclingTracker")
data class CyclingDataModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "distance")
    val distance: String,
    @ColumnInfo(name = "movingTime")
    val movingTime: String,
//    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "avgSpeed")
    val avgSpeed: String,
    @ColumnInfo(name = "startDate")
    val startDate: String,
    @ColumnInfo(name = "maxSpeed")
    val maxSpeed: String,
    @ColumnInfo(name = "locationCountry")
    val locationCountry: String,
    @ColumnInfo(name = "avgTemp")
    val avgTemp: String,
    @ColumnInfo(name = "hasHeartRate")
    val hasHeartRate: String,
    @ColumnInfo(name = "avgHeartRate")
    val avgHeartRate: String,
    @ColumnInfo(name = "maxHeartRate")
    val maxHeartRate: String,
    @ColumnInfo(name = "elevGain")
    val elevGain: String,
    @ColumnInfo(name = "elevHigh")
    val elevHigh: String,
    @ColumnInfo(name = "elevLow")
    val elevLow: String,
//    @ColumnInfo(name = "caloriesBurned")
//    val caloriesBurned: String
)