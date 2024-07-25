package com.example.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.example.myapplication.CollectWeekActivities
import com.example.myapplication.DataFetchWorker
import com.example.myapplication.RequestStravaData
import com.example.myapplication.adapters.DataAdapter
import com.example.myapplication.database.MainDB
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.interfaces.IStravaLoader
import com.example.myapplication.models.StravaDataModel
import com.example.myapplication.models.WeeklyRunningDataModel
import com.example.myapplication.models.toCyclingEntity
import com.example.myapplication.models.toRunningEntity
import kotlinx.coroutines.*
import androidx.work.Constraints
import androidx.work.NetworkType
import java.util.concurrent.TimeUnit


class HomeFragment : Fragment(), IStravaLoader {
    private lateinit var bindingClass: FragmentHomeBinding
    private val adapter = DataAdapter()
    private val loader = RequestStravaData(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingClass = FragmentHomeBinding.inflate(layoutInflater)
        init()
        return bindingClass.root
    }

    private fun init() {
        bindingClass.apply {
            rcViewMainScreen.adapter = adapter
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        refreshStravaToken()

        lifecycleScope.launch(Dispatchers.IO) {
            val collectWeekActivities = CollectWeekActivities()
            collectWeekActivities.execute(requireContext(), this@HomeFragment)
        }

    }

    override fun getCurrentContext(): Context {
        return requireContext()
    }


    override fun onStravaDataReady(data: StravaDataModel) {
        adapter.setStravaData(data)
//        Log.d("MyLog", "Saving data to DB")
        lifecycleScope.launch(Dispatchers.IO) {
            while (!isAdded) {
                delay(100)
            }
            val context = getCurrentContext()
            val db = MainDB.getDb(context)
            if (data.runningDataModel != null) {
                val dataRun = data.runningDataModel
                val toRunningEntity = dataRun!!.toRunningEntity()

                try {
                    db.getDao().insertRunningActivities(toRunningEntity)
                } catch (_: Exception) {
                }

            }
            if (data.cyclingDataModel != null) {
                val dataCycling = data.cyclingDataModel
                val toCyclingEntity = dataCycling!!.toCyclingEntity()
                try {
                    db.getDao().insertCyclingActivities(toCyclingEntity)
                } catch (_: Exception) {
                }
            }
        }
    }

    fun onWeeklyRunningDataReady(weeklyRunningDataModel: WeeklyRunningDataModel) {
        adapter.setWeeklyRunData(weeklyRunningDataModel)
    }

    fun refreshStravaToken(){
        loader.refreshToken { accessToken ->
            if (accessToken != null) {
                Log.d("MyLog", "refreshStravaToken runs")
                loader.getActivityInfo(accessToken)
            }
        }
    }


    override fun onStop() {
        super.onStop()

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            val periodicWorkRequest = PeriodicWorkRequest
                .Builder(DataFetchWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(requireContext()).enqueue(periodicWorkRequest)

    }


}