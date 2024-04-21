package com.example.myapplication.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.interfaces.IRecyclerItems
import com.example.myapplication.models.RunningDataModel


abstract class BaseDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindRunning(stravaRunData: RunningDataModel)


    abstract fun bindCycling(model: IRecyclerItems.CyclingDataModel)
}
