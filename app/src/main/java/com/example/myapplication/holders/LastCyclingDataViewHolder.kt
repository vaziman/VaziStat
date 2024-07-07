package com.example.myapplication.holders

import android.annotation.SuppressLint
import android.view.View
import com.example.myapplication.databinding.LastBikeBinding
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel

import com.example.myapplication.models.WeeklyRunningDataModel

class LastCyclingDataViewHolder(view : View): BaseDataViewHolder(view) {
    private val bindingClass = LastBikeBinding.bind(view)
    override fun bindRunning(stravaRunData: RunningDataModel?) {
    }

    @SuppressLint("SetTextI18n")
    override fun bindCycling(model: CyclingDataModel?) = with(bindingClass) {
        if (model != null){
            val name = model.name
            val distance = model.distance.toFloat() / 1000
            val distanceFormatted = String.format("%.2f", distance)
            val movingTimeMin = model.movingTime.toFloat() / 60
            val movingTimeSec = model.movingTime.toInt() % 60
            val avgSpeed = model.avgSpeed

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