package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.holders.BaseDataViewHolder
import com.example.myapplication.holders.LastCyclingDataViewHolder
import com.example.myapplication.holders.LastRunDataViewHolder
import com.example.myapplication.holders.WeeklyProgressDataViewHolder
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.StravaDataModel

class DataViewModel {
    private var stravaRunData: RunningDataModel? = null
    private var stravaCyclingData: CyclingDataModel? = null

    private val listofLayouts = listOf(
        R.layout.model_data_layout,
        R.layout.last_run,
        R.layout.last_bike
    )

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

    fun itemCount() : Int {
        if (stravaRunData == null){
            return 0
        }
        return listofLayouts.size
    }
    fun separateData(data: StravaDataModel) {
        val allData = data

    }


}