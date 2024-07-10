package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.interfaces.IDaoRoom

@Database(entities = [RunningEntity::class, CyclingEntity::class], version = 1)
abstract class MainDB: RoomDatabase(){

    abstract fun getDao(): IDaoRoom

    companion object{
        @Volatile
        private var INSTANCE: MainDB? = null

        fun getDb(context: Context): MainDB {
            synchronized(this) {
                var instance = INSTANCE

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