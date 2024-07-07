package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.holders.BaseDataViewHolder
import com.example.myapplication.holders.LastCyclingDataViewHolder
import com.example.myapplication.holders.LastRunDataViewHolder
import com.example.myapplication.holders.WeeklyProgressDataViewHolder
import com.example.myapplication.models.RunningDataModel

class DataViewModel {

    fun dataForCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseDataViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(viewType, viewGroup, false)
        if (viewType == R.layout.last_run) {

            return LastRunDataViewHolder(view)
        }
        if (viewType == R.layout.last_bike) {
            return LastCyclingDataViewHolder(view)
        }
        return WeeklyProgressDataViewHolder(view)
    }
}