package com.example.myapplication.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope


import com.example.myapplication.R
import com.example.myapplication.RequestStravaData
import com.example.myapplication.adapters.DataAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.interfaces.IRecyclerItems
import com.example.myapplication.interfaces.IStravaLoader
import com.example.myapplication.models.StravaDataModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), IStravaLoader {
    private lateinit var bindingClass: FragmentHomeBinding
    private val adapter = DataAdapter()
    private val dataList = listOf(
        R.id.rc_view_main_screen,

    )
    private var index = 0


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
//            val data = DataModel( progressBar = 50, dataKilometers = "00/00", dataKMPercent = "00/00%")
//            adapter.addData(data)


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
//        val token = loader.refreshToken()
//        if (token != null) {
//            loader.getActivityInfo(token)
//        }


//        lifecycleScope.launch {
//
//        }
    }

    override fun getCurrentContext(): Context {
        return requireContext()
    }

    override fun onStravaDataReady(data: StravaDataModel) {
        adapter.setStravaData(data)
    }



}