package com.example.myapplication.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.WeeklyRunningDataModel


abstract class BaseDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bindRunning(stravaRunData: RunningDataModel?)

    abstract fun bindCycling(model: CyclingDataModel?)

    abstract fun bindWeeklyProgress(weeklyData: WeeklyRunningDataModel?)
}
