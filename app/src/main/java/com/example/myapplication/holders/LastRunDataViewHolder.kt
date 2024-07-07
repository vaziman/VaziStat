package com.example.myapplication.holders

import android.annotation.SuppressLint
import android.view.View
import com.example.myapplication.databinding.LastRunBinding
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.WeeklyRunningDataModel


class LastRunDataViewHolder(view: View) : BaseDataViewHolder(view) {

    private val bindingClass = LastRunBinding.bind(view)

    @SuppressLint("SetTextI18n")
    override fun bindRunning(stravaRunData: RunningDataModel?) = with(bindingClass) {
        if (stravaRunData != null) {

            val km = stravaRunData.distance.toFloat() / 1000.0
            val formattedKilometers = String.format("%.2f", km)
            val peaceMin = (stravaRunData.movingTime.toFloat() / 60) / km
            val peaceMinFloat = (String.format("%.2f", peaceMin).toFloat() % 1) * 100
            val peaceSec = peaceMinFloat.toInt() * 60 / 100


            tvNameOfLastRun.text = stravaRunData.name
            tvTimeRun.text = "Time: ${stravaRunData.movingTime.toInt() / 60}:${stravaRunData.movingTime.toInt() % 60}"
            tvDistanceRun.text = "${formattedKilometers} KM"
            if (peaceSec < 10) {
                tvPeaceRun.text = "Peace: ${((stravaRunData.movingTime.toFloat() / 60) / km).toInt()}:0$peaceSec"
            } else {
                tvPeaceRun.text = "Peace: ${((stravaRunData.movingTime.toFloat() / 60) / km).toInt()}:$peaceSec"
            }
        } else {
            CLLastRun.visibility = View.GONE
        }


    }


    override fun bindCycling(model: CyclingDataModel?) = with(bindingClass) {

    }

    override fun bindWeeklyProgress(weeklyData: WeeklyRunningDataModel?) {

    }

}