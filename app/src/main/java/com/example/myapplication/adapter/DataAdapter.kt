package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ModelDataLayoutBinding
import com.example.myapplication.model.DataModel
import com.google.android.material.shape.TriangleEdgeTreatment

class DataAdapter(): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {
    private val dataList = ArrayList<DataModel>()


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DataViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.model_data_layout, p0, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(p0: DataViewHolder, p1: Int) {
        p0.bind(dataList[p1])
    }

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bindingClass = ModelDataLayoutBinding.bind(view)
        fun bind(model: DataModel) = with(bindingClass){
            tvCountOfKM.text = model.dataKilometers
            tvCountOfKmPercent.text = model.dataKMPercent
        }

    }
    fun addData(data: DataModel){
        dataList.add(data)
        notifyDataSetChanged() // refreshing data in RecyclerView list
    }
}
