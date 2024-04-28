package com.example.myapplication.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DataViewModel
import com.example.myapplication.R
import com.example.myapplication.holders.BaseDataViewHolder
import com.example.myapplication.holders.LastCyclingDataViewHolder
import com.example.myapplication.holders.LastRunDataViewHolder
import com.example.myapplication.holders.WeeklyProgressDataViewHolder
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.StravaDataModel

class DataAdapter() : RecyclerView.Adapter<BaseDataViewHolder>() {
    val dataViewHolder = DataViewModel()
    private val listofLayouts = listOf(
        R.layout.model_data_layout,
        R.layout.last_run,
        R.layout.last_bike
    )

    private var stravaRunData: RunningDataModel? = null
    private var stravaCyclingData: CyclingDataModel? = null
    private var weeklyProgressData: WeeklyProgressDataViewHolder? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseDataViewHolder {
        return dataViewHolder.dataForCreateViewHolder(viewGroup, viewType)
    }

//    private fun getViewHolder(view: View, viewType: Int): BaseDataViewHolder {
//        return when (viewType) {
//            R.layout.last_run -> LastRunDataViewHolder(view)
//            R.layout.last_bike -> LastCyclingDataViewHolder(view)
//            else -> WeeklyProgressDataViewHolder(view)
//        }
//    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.model_data_layout
            1 -> R.layout.last_run
            2 -> R.layout.last_bike
            else -> R.layout.model_data_layout
        }
//        return listLayouts[position]
    }

    override fun getItemCount(): Int {
        if (stravaRunData == null) {
            return 0
        }
        return listofLayouts.size
//        return dataViewHolder.itemCount()
    }


    override fun onBindViewHolder(dataViewHolder: BaseDataViewHolder, index: Int) {
        dataViewHolder.bindRunning(stravaRunData)
        dataViewHolder.bindCycling(stravaCyclingData)
//        dataViewHolder.bindWeeklyProgress(weeklyProgressData)

    }


    fun setStravaData(data: StravaDataModel) {
        val stravaDataModel = data
        stravaRunData = stravaDataModel.runningDataModel
        stravaCyclingData = stravaDataModel.cyclingDataModel
//        weeklyProgressData = stravaDataModel.weeklyProgressModel
        notifyDataSetChanged()
    }

}
