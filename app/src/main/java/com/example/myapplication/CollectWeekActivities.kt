package com.example.myapplication

import android.content.Context
import android.util.Log
import com.example.myapplication.database.MainDB
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.interfaces.IDaoRoom
import com.example.myapplication.models.WeeklyRunningDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset


class CollectWeekActivities {

    private fun getCurrentWeekDates(): Pair<String, String> {
        val now = LocalDate.now(ZoneOffset.UTC)

        val firstDayOfWeek = now.with(DayOfWeek.MONDAY)
        val lastDayOfWeek = firstDayOfWeek.plusDays(6)
        return Pair(firstDayOfWeek.toString(), lastDayOfWeek.toString())
    }

    private fun getCurrentWeekActivities(dao: IDaoRoom): Flow<Double> {
        val (startDate, endDate) = getCurrentWeekDates()
        val weekActivitiesFlow = dao.getActivitiesForCurrentWeek(startDate, endDate)
        return weekActivitiesFlow.map { activities ->
            activities
                .filter { it.type == "Run" }
                .sumOf { it.distance.toDouble() }
        }
    }

    suspend fun execute(context: Context, homeFragment: HomeFragment) {
        val db = MainDB.getDb(context)
        val dao = db.getDao()
        var totalDistance: Double

        val distanceFlow = getCurrentWeekActivities(dao)
        distanceFlow.collect { distance ->
            totalDistance = distance

            val weeklyRunningDataModel = WeeklyRunningDataModel(
                weeklyRunningDistance = totalDistance.toString()
            )
            withContext(Dispatchers.Main) {
                homeFragment.onWeeklyRunningDataReady(weeklyRunningDataModel)
            }
            Log.d("MyLog", "Total distance: $weeklyRunningDataModel")
        }
    }
}
