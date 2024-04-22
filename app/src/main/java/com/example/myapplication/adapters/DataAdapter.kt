package com.example.myapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.holders.BaseDataViewHolder
import com.example.myapplication.holders.LastCyclingDataViewHolder
import com.example.myapplication.holders.LastRunDataViewHolder
import com.example.myapplication.holders.WeeklyProgressDataViewHolder
import com.example.myapplication.interfaces.IRecyclerItems
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.StravaDataModel
import com.squareup.kotlinpoet.STRING

class DataAdapter() : RecyclerView.Adapter<BaseDataViewHolder>() {
//    private val dataList = ArrayList<DataModel>()
    private val listofLayouts = listOf(
        R.layout.model_data_layout,
        R.layout.last_run,
        R.layout.last_bike
    )


    private var myData: StravaDataModel? = null
    private var stravaRunData: RunningDataModel? = null
    private var stravaCyclingData: CyclingDataModel? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseDataViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(viewType, viewGroup, false)

//        val inflater = LayoutInflater.from(viewGroup.context)
//        val view = inflater.inflate(viewType,viewGroup, false)
//
//        return getViewHolder(view, viewType)

        if (viewType == R.layout.last_run) {

            return LastRunDataViewHolder(view)
        }
        if (viewType == R.layout.last_bike) {
            return LastCyclingDataViewHolder(view)
        }
        return WeeklyProgressDataViewHolder(view)
    }

    private fun getViewHolder(view: View, viewType: Int): BaseDataViewHolder {
        return when (viewType) {
            R.layout.last_run -> LastRunDataViewHolder(view)
            R.layout.last_bike -> LastCyclingDataViewHolder(view)
            else -> WeeklyProgressDataViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
//        dataItems[position].getItemViewType()
        return when(position){
            1 -> R.layout.last_run
            2 -> R.layout.last_bike
            else -> R.layout.model_data_layout
        }
//        return listLayouts[position]
    }

    override fun getItemCount(): Int {
        if (stravaRunData == null && stravaCyclingData == null){
            return 0
        }
        return listofLayouts.size


    }


    override fun onBindViewHolder(dataViewHolder: BaseDataViewHolder, index: Int) {
        dataViewHolder.bindRunning(stravaRunData!!)
        dataViewHolder.bindCycling(stravaCyclingData!!)
    }



    fun addData(data: StravaDataModel) {
//        dataList.add(data)
//        myData = data
//        notifyDataSetChanged() // refreshing data in RecyclerView list
    }

    fun setStravaData(data: StravaDataModel) {
        val stravaDataModel = data


        stravaRunData = stravaDataModel.runningDataModel
        stravaCyclingData = stravaDataModel.cyclingDataModel
        notifyDataSetChanged()
    }

}
