package com.example.myapplication.interfaces

import android.content.Context
import com.example.myapplication.models.StravaDataModel

interface IStravaLoader{
    fun onStravaDataReady(data: StravaDataModel)
    fun getCurrentContext(): Context
}