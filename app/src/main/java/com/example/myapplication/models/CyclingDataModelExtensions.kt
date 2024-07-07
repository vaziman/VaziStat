package com.example.myapplication.models

import com.example.myapplication.database.CyclingEntity
import com.example.myapplication.database.RunningEntity

fun CyclingDataModel.toCyclingEntity(): CyclingEntity {
    return CyclingEntity(
        name = name,
        distance = distance,
        movingTime = movingTime,
        type = type,
        avgSpeed = avgSpeed,
        startDate = startDate,
        maxSpeed = maxSpeed,
        locationCountry = locationCountry,
        avgTemp = avgTemp,
        hasHeartRate = hasHeartRate,
        avgHeartRate = avgHeartRate,
        maxHeartRate = maxHeartRate,
        elevGain = elevGain,
        elevHigh = elevHigh,
        elevLow = elevLow,
        idOfActivity = idOfActivity

        )
}