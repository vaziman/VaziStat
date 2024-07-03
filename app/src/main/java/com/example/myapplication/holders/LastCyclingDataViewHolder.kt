package com.example.myapplication.holders

import android.view.View
import com.example.myapplication.MainActivity
import com.example.myapplication.database.MainDB
import com.example.myapplication.databinding.LastBikeBinding
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.adapters.DataAdapter
import com.example.myapplication.models.WeeklyRunningDataModel

class LastCyclingDataViewHolder(view : View): BaseDataViewHolder(view) {
    val bindingClass = LastBikeBinding.bind(view)
//    val context = MainActivity().getCurrentContext()
//    val db = MainDB.getDb(context)
    override fun bindRunning(stravaRunData: RunningDataModel?) {
    }

    override fun bindCycling(stravaCyclingData: CyclingDataModel?) = with(bindingClass) {
        if (stravaCyclingData != null){
//            CVLastBike.visibility = View.VISIBLE
            val name = stravaCyclingData.name
            val distance = stravaCyclingData.distance.toFloat() / 1000
            val distanceFormatted = String.format("%.2f", distance)
            val movingTimeMin = stravaCyclingData.movingTime.toFloat() / 60
            val movingTimeSec = stravaCyclingData.movingTime.toInt() % 60
            val type = stravaCyclingData.type
            val avgSpeed = stravaCyclingData.avgSpeed

            tvNameOfLastBike.text = name
            tvDistanceBike.text = "$distanceFormatted KM"
            tvPeaceBike.text = "avgSpeed: $avgSpeed"
            if (movingTimeMin > 100){
                val movingTimeHour = (movingTimeMin / 60).toInt()
                val movingTimeMinRemainder = (movingTimeMin % 60).toInt()
                tvTimeBike.text = "Time: $movingTimeHour:$movingTimeMinRemainder:$movingTimeSec"
            }else{
                tvTimeBike.text = "Time: ${movingTimeMin.toInt()}:${movingTimeSec}"
            }

        } else CVLastBike.visibility = View.GONE
    }

    override fun bindWeeklyProgress(weeklyData: WeeklyRunningDataModel?) {

    }
}