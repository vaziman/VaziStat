package com.example.myapplication.holders

import android.view.View
import com.example.myapplication.databinding.LastRunBinding
import com.example.myapplication.models.DataModel

class LastRunDataViewHolder(view: View) : BaseDataViewHolder(view) {

    val bindingClass = LastRunBinding.bind(view)
    override fun bind(model: DataModel) = with(bindingClass) {
        tvNameOfLastRun.text = "Hi from logic"
//        tvCountOfKM.text = model.dataKilometers
//        tvCountOfKmPercent.text = model.dataKMPercent
    }
}