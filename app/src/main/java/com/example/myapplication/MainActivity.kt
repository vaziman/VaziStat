package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.constance.Constance

import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragments.ListMoreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)  // activation return button of toolbar
        // setSupportActionBar(findViewById(R.id.toolbarMain))

        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.bottomNavMenu)
        bottomNavigationMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {  // set listener on button in bottomMenu
                R.id.navigation_list -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.CLMain, ListMoreFragment.newInstance()).commit() //open fragment

                    true
                }

                else -> false
            }
        }

    }


}


//             supportActionBar?.apply {
//           setDisplayHomeAsUpEnabled(true)
//            setHomeAsUpIndicator(R.drawable.arrow_back_for_toolbar)
//        }



