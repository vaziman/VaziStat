package com.example.myapplication

import android.content.Context
import android.util.Log
import com.example.myapplication.database.MainDB
import com.example.myapplication.interfaces.IDaoRoom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*


class CollectWeekActivities() {

    var totalWeekDistance: Double = 0.0


    private fun getCurrentWeekDates(): Pair<String, String> {
        val now = LocalDate.now(ZoneOffset.UTC)

        val firstDayOfWeek = now.with(DayOfWeek.MONDAY)
        val lastDayOfWeek = firstDayOfWeek.plusDays(6)
        return Pair(firstDayOfWeek.toString(), lastDayOfWeek.toString())
    }

    private  fun getCurrentWeekActivities(dao: IDaoRoom): Flow<Double> {
        val (startDate, endDate) = getCurrentWeekDates()
        val weekActivitiesFlow = dao.getActivitiesForCurrentWeek(startDate, endDate)
        return weekActivitiesFlow.map { activities ->
            activities
                .filter { it.type == "Run" }
                .map { it.distance.toDouble() }
                .sum()

        }
    }

    suspend fun execute(context: Context): Double {

        val db = MainDB.getDb(context)
        val dao = db.getDao()
        var totalDistance = 0.0
        getCurrentWeekActivities(dao).collect{ distance ->
            totalDistance = distance
        }
        Log.d("MyLog", "Total distance: $totalDistance")
        return totalDistance
    }


}
