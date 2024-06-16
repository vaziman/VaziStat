package com.example.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.CollectWeekActivities


import com.example.myapplication.R
import com.example.myapplication.RequestStravaData
import com.example.myapplication.adapters.DataAdapter
import com.example.myapplication.database.CyclingEntity
import com.example.myapplication.database.MainDB
import com.example.myapplication.database.RunningEntity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.interfaces.IContextProvider
import com.example.myapplication.interfaces.IStravaLoader
import com.example.myapplication.models.StravaDataModel
import com.example.myapplication.models.toCyclingEntity
import com.example.myapplication.models.toRunningEntity
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.properties.Delegates


class HomeFragment: Fragment(), IStravaLoader {
    private lateinit var bindingClass: FragmentHomeBinding
    private val adapter = DataAdapter()
    private val pendingContinuations = mutableListOf<Continuation<Context>>()
    lateinit var stravaRunDataEntity: RunningEntity
    lateinit var stravaCyclingDataEntity: CyclingEntity
    var weekRunningActivities: Double? = null
    private val dataList = listOf(
        R.id.rc_view_main_screen,
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingClass = FragmentHomeBinding.inflate(layoutInflater)
        init()
        return bindingClass.root
    }
    private fun init(){
        bindingClass.apply {
            rcViewMainScreen.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loader = RequestStravaData(this)

        loader.refreshToken {accessToken ->
            if (accessToken != null){
                loader.getActivityInfo(accessToken)
            }
        }
        lifecycleScope.launch(Dispatchers.IO){
            val collectWeekActivities = CollectWeekActivities()
           val weekRunningDistance = collectWeekActivities.execute(requireContext())
//            Log.d("MyLog", "weekRunningDistance: $weekRunningDistance")
            weekRunningActivities = weekRunningDistance
        }
    }

    override fun getCurrentContext(): Context {
        return requireContext()
    }



    override fun onStravaDataReady(data: StravaDataModel) {
        adapter.setStravaData(data)
        lifecycleScope.launch(Dispatchers.IO) {
            while(!isAdded){
                delay(100)
                }
            val context = getCurrentContext()
            val db = MainDB.getDb(context)
            if (data.runningDataModel != null) {
                val dataRun = data.runningDataModel
                val toRunningEntity = dataRun!!.toRunningEntity()
//                val existingIdOfActivityInInEntity = db.getDao().getItemsFromRunningTracker(toRunningEntity.idOfActivity)
                try{
                    db.getDao().insertRunningActivities(toRunningEntity)
                }catch(e:Exception){}

            }
            if(data.cyclingDataModel != null){
                val dataCycling = data.cyclingDataModel
                val toCyclingEntity = dataCycling!!.toCyclingEntity()

                val idFromEntityCycling = toCyclingEntity.idOfActivity
                try{
                    db.getDao().insertCyclingActivities(toCyclingEntity)
                }catch (e:Exception){}

            }
        }
    }




    fun initRunEntityVariable(data: RunningEntity){
        stravaRunDataEntity = data
        Log.d("MyLog", "runEntityData1: ${stravaRunDataEntity }}")
    }
    fun initCyclingEntityVariable(data: CyclingEntity){
        stravaCyclingDataEntity = data
    }


    fun getStartAndEndDatesOfCurrentWeek(): Pair<String, String> {
        val calendar = Calendar.getInstance()

        // Set calendar to start of current week
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        val startDate = calendar.time

        // Set calendar to end of current week
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        val endDate = calendar.time

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return Pair(dateFormat.format(startDate), dateFormat.format(endDate))
    }


//    fun getCurrentWeekActivities(dao: IDaoRoom): Flow<List<RunningEntity>> {
//        val (startDate, endDate) = getStartAndEndDatesOfCurrentWeek()
//        val weekActivitiesFlow =  dao.getActivitiesForCurrentWeek(startDate, endDate)
//        CoroutineScope(Dispatchers.IO).launch {
//            weekActivitiesFlow.collect { activities ->
//                Log.d("MyLog", "Week Activities: ${activities.joinToString()}")
//            }
//        }
//        return weekActivitiesFlow
//    }
//    override suspend fun obtainContext(): Context {
//        return suspendCancellableCoroutine { continuation ->
//            val currentContext = context
//            if (currentContext != null) {
//                continuation.resume(currentContext)
//            } else {
//                viewLifecycleOwner.lifecycleScope.launch {
//                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
//                        override fun onStart(owner: LifecycleOwner) {
//                            try {
//                                if (continuation.isActive) {
//                                    continuation.resume(requireContext())
//                                }
//                            } catch (e: IllegalStateException) {
//                                if (continuation.isActive) {
//                                    continuation.resumeWithException(e)
//                                }
//                            }
//                            owner.lifecycle.removeObserver(this)
//                        }
//                    })
//                }
//            }
//        }
//    }

}