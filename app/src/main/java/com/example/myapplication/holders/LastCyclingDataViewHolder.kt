package com.example.myapplication.holders

import android.view.View
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.databinding.LastBikeBinding
import com.example.myapplication.interfaces.IRecyclerItems
import com.example.myapplication.models.RunningDataModel

class LastCyclingDataViewHolder(view : View): BaseDataViewHolder(view) {
    val bindingClass = LastBikeBinding.bind(view)
    override fun bindRunning(stravaRunData: RunningDataModel) {
    }

    override fun bindCycling(model: IRecyclerItems.CyclingDataModel) = with(bindingClass) {
        tvNameOfLastBike.text = "123"

    }
}