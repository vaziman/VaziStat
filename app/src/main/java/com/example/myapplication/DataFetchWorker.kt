package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplication.fragments.HomeFragment

class DataFetchWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        return try {
            Log.d("MyLog", "Work Manager")
            val requestStravaData = RequestStravaData(HomeFragment())
            requestStravaData.refreshToken {
                it?.let {
                    Log.d("MyLog", "token is not null -> $it")
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}