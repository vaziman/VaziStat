package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.holders.BaseDataViewHolder
import com.example.myapplication.holders.LastRunDataViewHolder
import com.example.myapplication.holders.WeeklyProgressDataViewHolder
import com.example.myapplication.model.DataModel

class DataAdapter() : RecyclerView.Adapter<BaseDataViewHolder>() {
//    private val dataList = ArrayList<DataModel>()


    private var myData: DataModel? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseDataViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(viewType, viewGroup, false)

        if (viewType == R.layout.last_run) {
            return LastRunDataViewHolder(view)
        }
        return WeeklyProgressDataViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
//        dataItems[position].getItemViewType()
        if (position == 1) {
            return R.layout.last_run
        }
        return R.layout.model_data_layout
    }

    override fun getItemCount(): Int {
//        return dataList.size
//        return 3
        if (myData == null) {
            return 0
        }
        return 2
    }


    override fun onBindViewHolder(dataViewHolder: BaseDataViewHolder, index: Int) {
        dataViewHolder.bind(myData!!)
//        val pbItem = dataList[0]
//        val countOfKm = dataList[1]
//        val kmPercent = dataList[3]
//        p0.bind(pbItem)
//        p0.bind(countOfKm)
//        p0.bind(kmPercent)



    }



    fun addData(data: DataModel) {
//        dataList.add(data)
        myData = data
        notifyDataSetChanged() // refreshing data in RecyclerView list
    }

}
