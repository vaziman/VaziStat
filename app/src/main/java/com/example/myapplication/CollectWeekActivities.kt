package com.example.myapplication

import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.View
import com.example.myapplication.adapters.DataAdapter
import com.example.myapplication.database.MainDB
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.holders.WeeklyProgressDataViewHolder
import com.example.myapplication.interfaces.IDaoRoom
import com.example.myapplication.models.StravaDataModel
import com.example.myapplication.models.WeeklyRunningDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class CollectWeekActivities() {

    var totalWeekDistance: Double = 0.0
//    val stravaDataModel = StravaDataModel()


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
                .map { it.distance.toDouble() }
                .sum()

        }
    }

    suspend fun execute(context: Context, homeFragment: HomeFragment) {
//        val view = View(context)
//        val weeklyRunningDataView = WeeklyProgressDataViewHolder(view)
//        val dataAdapter = DataAdapter()

//        val stravaDataModel = StravaDataModel()
        val db = MainDB.getDb(context)
        val dao = db.getDao()
        var totalDistance = 0.0

        val distanceFlow = getCurrentWeekActivities(dao)
        distanceFlow.collect { distance ->
            totalDistance = distance

            val weeklyRunningDataModel = WeeklyRunningDataModel(
                weeklyRunningDistance = totalDistance.toString()
            )
            withContext(Dispatchers.Main) {
                homeFragment.onWeeklyRunningDataReady(weeklyRunningDataModel)
            }
//            stravaDataModel.weeklyProgressDataModel = weeklyRunningDataModel
//            dataAdapter.setWeeklyRunData(stravaDataModel)
//            weeklyRunningDataView.updateWeekRunningDistanceVariable(totalDistance)
            Log.d("MyLog", "Total distance: $weeklyRunningDataModel")
        }

    }
}
