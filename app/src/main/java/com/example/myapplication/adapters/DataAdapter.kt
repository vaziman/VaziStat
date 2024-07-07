package com.example.myapplication.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DataViewModel
import com.example.myapplication.R
import com.example.myapplication.holders.BaseDataViewHolder
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.StravaDataModel
import com.example.myapplication.models.WeeklyRunningDataModel

class DataAdapter() : RecyclerView.Adapter<BaseDataViewHolder>() {
    val dataViewHolder = DataViewModel()
    lateinit var thisContext: Context
    private val listofLayouts = listOf(
        R.layout.progress_bar_run,
        R.layout.last_run,
        R.layout.last_bike
    )

    private var stravaRunData: RunningDataModel? = null
    private var stravaCyclingData: CyclingDataModel? = null
    private var weeklyProgressData: WeeklyRunningDataModel? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseDataViewHolder {
        return dataViewHolder.dataForCreateViewHolder(viewGroup, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> R.layout.progress_bar_run
            1 -> R.layout.last_run
            2 -> R.layout.last_bike
            else -> R.layout.progress_bar_run
        }
    }

    override fun getItemCount(): Int {
        if (stravaRunData == null) {
            return 0
        }
        return listofLayouts.size
    }


    override fun onBindViewHolder(dataViewHolder: BaseDataViewHolder, index: Int) {
        dataViewHolder.bindRunning(stravaRunData)
        dataViewHolder.bindCycling(stravaCyclingData)
        dataViewHolder.bindWeeklyProgress(weeklyProgressData)

    }


    fun setStravaData(data: StravaDataModel) {
        val stravaDataModel = data
        stravaRunData = stravaDataModel.runningDataModel
        stravaCyclingData = stravaDataModel.cyclingDataModel
        notifyDataSetChanged()
    }

    fun setDataContext(context: Context){
        thisContext = context
    }

    fun setWeeklyRunData(data: WeeklyRunningDataModel){
        weeklyProgressData = data
        notifyDataSetChanged()
    }
}
