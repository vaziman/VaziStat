package com.example.myapplication.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.DataModel


abstract class BaseDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: DataModel)


}
