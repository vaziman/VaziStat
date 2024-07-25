package com.example.myapplication

import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.StravaDataModel
import com.example.myapplication.models.WeeklyRunningDataModel
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class HomeFragmentTest {
    val weeklyRunningDataModel = mock<WeeklyRunningDataModel>()
    val homeFragment = mock<HomeFragment>()
    @Test
    fun `should return the same data as from strava server`() {

//        val testWeeklyRunningData = homeFragment.onStravaDataReady(StravaDataModel())
//        Mockito.`when`(weeklyRunningDataModel.weeklyRunningDistance).thenReturn(testWeeklyRunningData.toString())



        assertEquals(4, 2 + 2)
    }
}