package com.example.myapplication.holders

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.example.myapplication.databinding.ProgressBarRunBinding
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.WeeklyRunningDataModel

class WeeklyProgressDataViewHolder(view: View) : BaseDataViewHolder(view) {
    private val bindingClass = ProgressBarRunBinding.bind(view)


    override fun bindRunning(stravaRunData: RunningDataModel?) {
    }


    override fun bindCycling(model: CyclingDataModel?) {
    }

    @SuppressLint("SetTextI18n")
    override fun bindWeeklyProgress(weeklyData: WeeklyRunningDataModel?) = with(bindingClass) {
        progressBarRunning.max = 50
        var currentProgress = "0.0"
        var percent = currentProgress.toFloat() / progressBarRunning.max.toFloat() * 100
        tvCountOfKM.text = "$currentProgress / ${progressBarRunning.max}km"
        tvCountOfKmPercent.text = "%.1f%%".format(percent)
        Log.d("MyLog", "Weekly Data: $weeklyData")

        if (weeklyData != null) {
            currentProgress = (weeklyData.weeklyRunningDistance.toFloat() / 1000.0).toString()

            tvCountOfKM.text = "$currentProgress / ${progressBarRunning.max}km"
            percent = currentProgress.toFloat() / progressBarRunning.max.toFloat() * 100
            tvCountOfKmPercent.text = "%.1f%%".format(percent)
            Log.d("MyLog", "Week Activities for view: $currentProgress")

        }
    }
}