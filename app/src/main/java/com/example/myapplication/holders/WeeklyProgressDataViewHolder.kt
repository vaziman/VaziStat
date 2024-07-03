package com.example.myapplication.holders

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import com.example.myapplication.CollectWeekActivities

import com.example.myapplication.database.MainDB
import com.example.myapplication.database.RunningEntity
import com.example.myapplication.databinding.ProgressBarRunBinding
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.interfaces.IContextProvider
import com.example.myapplication.interfaces.IDaoRoom
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.WeeklyRunningDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.CollationElementIterator
import java.text.SimpleDateFormat
import java.util.*

class WeeklyProgressDataViewHolder(view: View): BaseDataViewHolder(view) {
    val bindingClass = ProgressBarRunBinding.bind(view)
    var weekRunningDistance :Double? = null



    override fun bindRunning(stravaRunData: RunningDataModel?){
    }


    override fun bindCycling(model: CyclingDataModel?) {
    }

    override fun bindWeeklyProgress(weeklyData: WeeklyRunningDataModel?) = with(bindingClass) {
        progressBarRunning.max = 50
        var currentProgress = "5.5"
        var percent = currentProgress.toFloat() / progressBarRunning.max.toFloat() * 100
 //      ObjectAnimator.ofInt(progressBarRunning, "progress", currentProgress).start()
//
        tvCountOfKM.text = "$currentProgress / ${progressBarRunning.max}km"
        tvCountOfKmPercent.text = "%.1f%%".format(percent)
        Log.d("MyLog", "Weekly Data: ${weeklyData}")


        if(weeklyData != null){
            currentProgress = weeklyData.weeklyRunningDistance
            tvCountOfKM.text = "$currentProgress / ${progressBarRunning.max}km"
            percent = currentProgress.toFloat() / progressBarRunning.max.toFloat() * 100
            tvCountOfKmPercent.text = "%.1f%%".format(percent)
            Log.d("MyLog", "Week Activities for view: ${currentProgress}")

        }


    }

    fun updateWeekRunningDistanceVariable(data: Double){
        weekRunningDistance = data
        Log.d("MyLog", "Week Activities for view: ${weekRunningDistance}")
    }
}