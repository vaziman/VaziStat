package com.example.myapplication.interfaces

interface IRecyclerItems{

    data class RunningDataModel(
        val name: String,
        val distance: String,
        val movingTime: String,
        val type: String
    )

    data class CyclingDataModel(
        val name: String,
        val distance: String,
        val movingTime: String,
        val type: String,
        val avgSpeed: String
    )
}