package com.example.myapplication.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.RunningDataModel


abstract class BaseDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(stravaRunData: RunningDataModel)


    abstract fun bindRunning(model: RunningDataModel)
}
