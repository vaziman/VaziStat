package com.example.myapplication.holders

import android.view.View
import com.example.myapplication.RequestStravaData
import com.example.myapplication.databinding.LastRunBinding
import com.example.myapplication.models.DataModel
import com.example.myapplication.models.RunningDataModel
import kotlin.contracts.contract

class LastRunDataViewHolder(view: View) : BaseDataViewHolder(view) {
    val bindingClass = LastRunBinding.bind(view)

    override fun bind(model: DataModel) = with(bindingClass) {
//        tvNameOfLastRun.text = "Hi from logic"
//        tvCountOfKM.text = model.dataKilometers
//        tvCountOfKmPercent.text = model.dataKMPercent
    }

    fun bindRunning(model: RunningDataModel) = with(bindingClass){


        tvNameOfLastRun.text = model.name
    }
}