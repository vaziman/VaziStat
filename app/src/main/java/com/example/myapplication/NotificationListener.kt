package com.example.myapplication

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.example.myapplication.fragments.HomeFragment

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
//        sbn?.let{
//            val applicationName = sbn.packageName
//            val notification = sbn.notification
//            val extras = notification.extras
//            val title = extras.getString("android.title")
//            val text = extras.getCharSequence("android.text")
//            Log.d("MyLog", "Name of Application: $applicationName, Title: $title, Text: $text")
//
//            if (title == "New workout" || text?.contains("workout") == true) {
       //          start of data downloading process
//                val loader = RequestStravaData()
//            }
//        }

//        Log.d("MyLog", "Notification posted: ${sbn?.notification?.tickerText}")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
  //      Log.d("MyNotificationService", "Notification removed: ${sbn?.notification?.tickerText}")
    }
}
