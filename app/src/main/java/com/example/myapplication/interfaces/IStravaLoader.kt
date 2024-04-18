package com.example.myapplication.interfaces

import android.content.Context
import com.example.myapplication.models.RunningDataModel

interface IStravaLoader{
    fun onStravaDataReady(data: RunningDataModel)
    fun getCurrentContext(): Context
}