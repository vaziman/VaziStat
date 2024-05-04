package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runningTracker")
data class RunningEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "distance")
    val distance: String,
    @ColumnInfo(name = "movingTime")
    val movingTime: String,
    val type: String,
    @ColumnInfo(name = "startDate")
    val startDate: String,
    @ColumnInfo(name = "locationCountry")
    val locationCountry: String,
    @ColumnInfo(name = "avgCadence")
    val avgCadence: String,
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
)