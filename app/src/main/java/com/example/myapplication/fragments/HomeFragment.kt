package com.example.myapplication.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.adapter.DataAdapter
import com.example.myapplication.constants.Constants
import com.example.myapplication.constants.Keys
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.DataModel
import java.util.zip.Inflater

class HomeFragment : Fragment() {
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
            val data = DataModel( progressBar = 50, dataKilometers = "00/00", dataKMPercent = "00/00%")
            adapter.addData(data)


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val urlForGet = "http://www.strava.com/oauth/authorize?client_id=${Keys.STRAVA_CLIENT_ID}&response_type=code&redirect_uri=http://localhost/exchange_token&approval_prompt=force&scope=read"



//        var progressBarRunning = bindingClass.rcViewMainScreen
//        progressBarRunning.max = 50
//        var currentProgress = 44
//        ObjectAnimator.ofInt(progressBarRunning, "progress", currentProgress).start()
//
//        var percentOfKm = currentProgress.toFloat() / progressBarRunning.max.toFloat() * 100
//
//        bindingClass.tvCountOfKM.text = "$currentProgress/${progressBarRunning.max}km"
//        bindingClass.tvCountOfKmPercent.text = "${"%.1f".format(percentOfKm)}%"
    }


}