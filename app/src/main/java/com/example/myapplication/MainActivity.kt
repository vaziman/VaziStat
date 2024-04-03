package com.example.myapplication

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonToken
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.constants.Keys

import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.fragments.ListMoreFragment
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val requestStravaData = RequestStravaData(this)


        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        val toolbar = findViewById<Toolbar>(R.id.toolbarMain)
        setSupportActionBar(toolbar)  // activation return button of toolbar
        // setSupportActionBar(findViewById(R.id.toolbarMain))

        replaceFragment(HomeFragment()) // set homeFragment on Main screen

        bindingClass.bottomNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> replaceFragment(HomeFragment()) //open homeFragment when click on button
                R.id.navigation_list -> replaceFragment(ListMoreFragment())
                else -> {}
            }
            true
        }
             requestStravaData.getStravaAutorisationCode()
             requestStravaData.refreshToken()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, fragment)
        fragmentTransaction.commit()
    }

}




