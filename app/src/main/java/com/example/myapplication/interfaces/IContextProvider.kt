package com.example.myapplication.interfaces

import android.content.Context

interface IContextProvider {
    suspend fun obtainContext(): Context
    abstract fun getCurrentContext(): Context
}