package com.example.myapplication.interfaces

import android.content.Context

interface IStravaLoader{
    fun onStravaDataReady(data: IRecyclerItems.RunningDataModel)
    fun getCurrentContext(): Context
}