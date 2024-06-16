package com.example.myapplication.holders

import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.View
import com.example.myapplication.CollectWeekActivities

import com.example.myapplication.database.MainDB
import com.example.myapplication.database.RunningEntity
import com.example.myapplication.databinding.ModelDataLayoutBinding
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.interfaces.IContextProvider
import com.example.myapplication.interfaces.IDaoRoom
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.CollationElementIterator
import java.text.SimpleDateFormat
import java.util.*

class WeeklyProgressDataViewHolder(view: View): BaseDataViewHolder(view) {
    val bindingClass = ModelDataLayoutBinding.bind(view)



    override fun bindRunning(stravaRunData: RunningDataModel?) = with(bindingClass) {
    }


    override fun bindCycling(model: CyclingDataModel?) {
    }

    override fun bindWeeklyProgress(model: WeeklyProgressDataViewHolder?) = with(bindingClass) {
        progressBarRunning.max = 50
        var currentProgress = 0
        var percent = currentProgress.toFloat() / progressBarRunning.max.toFloat() * 100
        val weekRunningDistance = HomeFragment().weekRunningActivities
        Log.d("MyLog", "Week Activities for view: $weekRunningDistance")
//        val collectWeekActivities = CollectWeekActivities().totalWeekDistance
//

//        val contextProvider = object : IContextProvider {
//            override suspend fun obtainContext(): Context {
//                return HomeFragment().obtainContext()
//            }
//        }
//
//           CoroutineScope(Dispatchers.Main).launch {
//           collectWeekActivities.execute(contextProvider)
//               val totalRunningWeekDistance = collectWeekActivities.totalWeekDistance
//               Log.d("MyLog", "Week Activities for view: ${totalRunningWeekDistance}")
//           }
//



        ObjectAnimator.ofInt(progressBarRunning, "progress", currentProgress).start()

        tvCountOfKM.text = "$currentProgress / ${progressBarRunning.max}km"
        tvCountOfKmPercent.text = "%.1f%%".format(percent)

    }
}