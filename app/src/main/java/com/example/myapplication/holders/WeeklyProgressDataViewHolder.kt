package com.example.myapplication.holders

import android.animation.ObjectAnimator
import android.view.View
import com.example.myapplication.databinding.ModelDataLayoutBinding
import com.example.myapplication.interfaces.IRecyclerItems

class WeeklyProgressDataViewHolder(view: View) : BaseDataViewHolder(view) {

    val bindingClass = ModelDataLayoutBinding.bind(view)
    override fun bindRunning(stravaRunData: IRecyclerItems.RunningDataModel) = with(bindingClass) {
//        tvCountOfKM.text = model.dataKilometers
//        tvCountOfKmPercent.text = model.dataKMPercent

//        val progressBar = dataViewHolder.itemView.findViewById<ProgressBar>(R.id.progress_bar_running)
//        val dataKilometers = dataViewHolder.itemView.findViewById<TextView>(R.id.tvCountOfKM)
//        val dataKMPercent = dataViewHolder.itemView.findViewById<TextView>(R.id.tvCountOfKmPercent)
//
//        val lastRun = dataViewHolder.itemView.findViewById<CardView>(R.id.CVLastRun)



        progressBarRunning.max = 50
        var currentProgress = 44
        var percent = currentProgress.toFloat() / progressBarRunning.max.toFloat() * 100

        ObjectAnimator.ofInt(progressBarRunning, "progress", currentProgress).start()

        tvCountOfKM.text = "$currentProgress / ${progressBarRunning.max}km"
        tvCountOfKmPercent.text = "%.1f%%".format(percent)
    }

    override fun bindCycling(model: IRecyclerItems.CyclingDataModel) {
        TODO("Not yet implemented")
    }


}