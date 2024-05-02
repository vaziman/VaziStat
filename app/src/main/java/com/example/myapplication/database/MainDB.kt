package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


abstract class MainDB: RoomDatabase(){

    companion object{
        fun getDb(context: Context): MainDB{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "workoutTrackerDB.db"
            ).build()
        }
    }
}