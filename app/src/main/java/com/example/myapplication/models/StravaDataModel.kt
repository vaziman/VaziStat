package com.example.myapplication.models

import com.example.myapplication.database.RunningEntity

class StravaDataModel {
    public var runningDataModel: RunningDataModel? = null
    public var cyclingDataModel: CyclingDataModel? = null
    public var runningEntity: RunningEntity? = null
    public var weeklyProgressDataModel: WeeklyRunningDataModel? = null
}