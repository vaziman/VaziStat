package com.example.myapplication.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope


import com.example.myapplication.R
import com.example.myapplication.RequestStravaData
import com.example.myapplication.adapters.DataAdapter
import com.example.myapplication.database.CyclingEntity
import com.example.myapplication.database.MainDB
import com.example.myapplication.database.RunningEntity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.interfaces.IStravaLoader
import com.example.myapplication.models.StravaDataModel
import com.example.myapplication.models.toCyclingEntity
import com.example.myapplication.models.toRunningEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment: Fragment(), IStravaLoader {
    private lateinit var bindingClass: FragmentHomeBinding
    private val adapter = DataAdapter()
    lateinit var stravaRunDataEntity: RunningEntity
    lateinit var stravaCyclingDataEntity: CyclingEntity
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
    }

    override fun getCurrentContext(): Context {
        return requireContext()
    }

    override fun onStravaDataReady(data: StravaDataModel) {
        adapter.setStravaData(data)


        lifecycleScope.launch(Dispatchers.IO) {
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

}