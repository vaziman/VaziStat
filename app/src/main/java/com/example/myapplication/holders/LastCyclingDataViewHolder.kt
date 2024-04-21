package com.example.myapplication.holders

import android.view.View
import com.example.myapplication.databinding.LastBikeBinding
import com.example.myapplication.interfaces.IRecyclerItems

class LastCyclingDataViewHolder(view : View): BaseDataViewHolder(view) {
    val bindingClass = LastBikeBinding.bind(view)
    override fun bindRunning(stravaRunData: IRecyclerItems.RunningDataModel) {
    }

    override fun bindCycling(model: IRecyclerItems.CyclingDataModel) = with(bindingClass) {
        tvNameOfLastBike.text = "123"
    }
}