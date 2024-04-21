package com.example.myapplication.holders

import android.view.View
import com.example.myapplication.databinding.LastRunBinding
import com.example.myapplication.interfaces.IRecyclerItems

class LastRunDataViewHolder(view: View) : BaseDataViewHolder(view) {
    val bindingClass = LastRunBinding.bind(view)

    override fun bindRunning(stravaRunData: IRecyclerItems.RunningDataModel) = with(bindingClass) {
        val km = stravaRunData.distance.toFloat() / 1000.0
        val formattedKilometers = String.format("%.2f", km)
        val peaceMin = (stravaRunData.movingTime.toFloat() / 60) / km
        val peaceMinFloat = (String.format("%.2f", peaceMin).toFloat() % 1) * 100
        val peaceSec = peaceMinFloat.toInt() * 60 / 100


        tvNameOfLastRun.text = stravaRunData.name
        tvTimeRun.text = "Time: ${stravaRunData.movingTime.toInt() / 60}:${stravaRunData.movingTime.toInt() % 60}"
        tvDistanceRun.text= "${formattedKilometers} KM"
        tvPeaceRun.text = "Peace: ${((stravaRunData.movingTime.toFloat() / 60) / km).toInt()}:$peaceSec "

    }


    override fun bindCycling(model: IRecyclerItems.CyclingDataModel)= with(bindingClass){

    }
}