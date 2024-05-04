package com.example.myapplication.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.database.CyclingEntity
import com.example.myapplication.database.RunningEntity
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface IDaoRoom {
    @Insert
    fun insertRunningActivities(itemRunning: RunningEntity)

    //@Insert
    //fun insertCyclingActivities(itemCycling: CyclingEntity)
    @Query("SELECT * FROM runningTracker")
    fun getItemsFromRunningTracker(): Flow<List<RunningEntity>>
}