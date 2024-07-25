package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.myapplication.adapters.DataAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.fragments.ListMoreFragment
import com.example.myapplication.interfaces.IStravaLoader
import com.example.myapplication.models.StravaDataModel


class MainActivity : AppCompatActivity(), IStravaLoader {
    private lateinit var bindingClass: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        replaceFragment(HomeFragment()) // set homeFragment on Main screen

        val dataAdapter = DataAdapter()
        dataAdapter.setDataContext(this)


        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)  // activation return button of toolbar




        bindingClass.bottomNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> replaceFragment(HomeFragment()) //open homeFragment when click on button
                R.id.navigation_list -> replaceFragment(ListMoreFragment())
                else -> {}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onStravaDataReady(data: StravaDataModel) {
        TODO("Not yet implemented")
    }

    override fun getCurrentContext(): Context {
        return applicationContext
    }

}




