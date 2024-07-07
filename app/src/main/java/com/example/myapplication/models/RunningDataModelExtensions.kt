package com.example.myapplication.models

import com.example.myapplication.database.RunningEntity

fun RunningDataModel.toRunningEntity(): RunningEntity{
    return RunningEntity(
        name = name,
        distance = distance,
        movingTime = movingTime,
        type = type,
        startDate = startDate,
        locationCountry = locationCountry,
        avgCadence = avgCadence,
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