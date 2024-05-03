package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.interfaces.IDaoRoom
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel

@Database(entities = [RunningDataModel::class, CyclingDataModel::class], version = 1)
abstract class MainDB: RoomDatabase(){

    abstract fun getDao(): IDaoRoom


    companion object{

        @Volatile
        private var INSTANCE: MainDB? = null

        fun getDb(context: Context): MainDB {
            // sync to avoid of creating few DB in one time
            synchronized(this) {
                var instance = INSTANCE

                // Create DB if DB hasn't been created yet
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDB::class.java,
                        "workoutTrackerDB.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}