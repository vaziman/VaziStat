package com.example.myapplication.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.database.CyclingEntity
import com.example.myapplication.database.RunningEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IDaoRoom {
    @Insert
    fun insertRunningActivities(itemRunning: RunningEntity)

    @Insert
    fun insertCyclingActivities(itemCycling: CyclingEntity)

    @Query("SELECT * FROM runningTracker")
    fun getItemsFromRunningTracker(): Flow<List<RunningEntity>>

    @Query("""
        SELECT * FROM runningTracker
        WHERE date(startDate) >= date(:startDate)
        AND date(startDate) <= date(:endDate)
    """)
    fun getActivitiesForCurrentWeek(startDate: String, endDate: String): Flow<List<RunningEntity>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIfNotExistCycling(entity: CyclingEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIfNotExistRunning(entity: RunningEntity)

}