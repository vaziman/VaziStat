package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navMenu = view.findViewById<BottomNavigationView>(R.id.bottomNavMenu)
        val controller = findNavController()

        navMenu.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.navigation_list ->{
                    controller.navigate(R.id.listMoreFragment)
                    true
                }else -> false
            }

        }

    }

}