package com.example.myapplication.holders

import android.util.Log
import android.view.View
import com.example.myapplication.databinding.LastRunBinding
import com.example.myapplication.models.RunningDataModel

class LastRunDataViewHolder(view: View) : BaseDataViewHolder(view) {
    val bindingClass = LastRunBinding.bind(view)

    override fun bind(stravaRunData: RunningDataModel) = with(bindingClass) {
        val km = stravaRunData.distance.toFloat() / 1000.0
        val formattedKilometers = String.format("%.2f", km)
        val peaceMin = (stravaRunData.movingTime.toFloat() / 60) / km
        val peaceMinFloat = (String.format("%.2f", peaceMin).toFloat() % 1) * 100
        val peaceSec = peaceMinFloat.toInt() * 60 / 100

        tvNameOfLastRun.text = stravaRunData.name
        tvTime.text = "Time: ${stravaRunData.movingTime.toInt() / 60}:${stravaRunData.movingTime.toInt() % 60}"
        tvDistance.text= "${formattedKilometers} KM"
        tvPeace.text = "Peace: ${((stravaRunData.movingTime.toFloat() / 60) / km).toInt()}:$peaceSec "


//        tvCountOfKM.text = model.dataKilometers
//        tvCountOfKmPercent.text = model.dataKMPercent

    }


    override fun bindRunning(model: RunningDataModel): Unit = with(bindingClass){
        Log.d("MyTag","stravaList: $model")
//        tvNameOfLastRun.text = model.name
    }
}