package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.myapplication.adapters.DataAdapter
import com.example.myapplication.database.MainDB
import com.example.myapplication.interfaces.IStravaLoader
import com.example.myapplication.models.StravaDataModel
import com.example.myapplication.models.toCyclingEntity
import com.example.myapplication.models.toRunningEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataFetchWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params), IStravaLoader {
    override suspend fun doWork(): Result {
        return try {
            Log.d("MyLog", "Work Manager")
            val loader = RequestStravaData(this)

            loader.refreshToken { accessToken ->
                if (accessToken != null) {
                    Log.d("MyLog", "refreshStravaToken runs")
                    loader.getActivityInfo(accessToken)
                }
            }

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }


    override fun onStravaDataReady(data: StravaDataModel) {
        val adapter = DataAdapter()
        adapter.setStravaData(data)


        val context = getCurrentContext()
        val db = MainDB.getDb(context)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("MyLog", "Saving data to DB")
                if (data.runningDataModel != null) {
                    val dataRun = data.runningDataModel
                    val toRunningEntity = dataRun!!.toRunningEntity()
                    try {
                        db.getDao().insertRunningActivities(toRunningEntity)
                    } catch (_: Exception) {
                    }
                }
                if (data.cyclingDataModel != null) {
                    Log.d("MyLog", "Saving Cycling data to DB")
                    val dataCycling = data.cyclingDataModel
                    val toCyclingEntity = dataCycling!!.toCyclingEntity()
                    try {
                        db.getDao().insertCyclingActivities(toCyclingEntity)
                    } catch (_: Exception) {
                    }

                }
            } catch (_: Exception) {
            }
        }
    }

    override fun getCurrentContext(): Context {
        return applicationContext
    }
}