package com.example.myapplication.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import java.util.zip.Inflater

class HomeFragment : Fragment() {
    private lateinit var bindingClass: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingClass = FragmentHomeBinding.inflate(layoutInflater)
        return bindingClass.root
        // return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var progressBarRunning = bindingClass.progressBarRunning
        progressBarRunning.max = 50
        var currentProgress = 44
        ObjectAnimator.ofInt(progressBarRunning, "progress", currentProgress).start()

        var percentOfKm = currentProgress.toFloat() / progressBarRunning.max.toFloat() * 100

        bindingClass.tvCountOfKM.text = "$currentProgress/${progressBarRunning.max}km"
        bindingClass.tvCountOfKmPercent.text = "${"%.1f".format(percentOfKm)}%"
    }
}