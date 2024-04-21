package com.example.myapplication.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.interfaces.IRecyclerItems


abstract class BaseDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindRunning(stravaRunData: IRecyclerItems.RunningDataModel)


    abstract fun bindCycling(model: IRecyclerItems.CyclingDataModel)
}
