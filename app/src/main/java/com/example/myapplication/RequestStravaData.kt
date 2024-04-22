package com.example.myapplication

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.constants.Keys
import com.example.myapplication.interfaces.IRecyclerItems
import com.example.myapplication.interfaces.IStravaLoader
import com.example.myapplication.models.CyclingDataModel
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.models.StravaDataModel
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine



class RequestStravaData(private val listener: IStravaLoader) {

    fun getStravaAutorisationCode() {

        val urlAuthorizeCode = "https://www.strava.com/oauth/token?client_id=${Keys.STRAVA_CLIENT_ID}" +
                "&client_secret=${Keys.STRAVA_CLIENT_SECRET}" +
                "&code=${Keys.STRAVA_AUTORISATION_CODE}" +
                "&grant_type=authorization_code"
        val queue = Volley.newRequestQueue(listener.getCurrentContext())
        val stringRequest = StringRequest(
            Request.Method.POST, urlAuthorizeCode,
            { response ->
                val stringResponse = JSONObject(response)
                val accessToken = stringResponse.getJSONObject("access_token")

                // Log.d("MyLog", "Response: $stringResponse")
                //Log.d("MyLog", "Acceess_token: $accessToken")
            }, {
//                 Log.d("MyLog", "Response: $it")
            })
        queue.add(stringRequest)
    }

    suspend fun refreshToken(): String? = suspendCoroutine { continuation ->
        val url = "https://www.strava.com/oauth/token" +
                "?client_id=${Keys.STRAVA_CLIENT_ID}" +
                "&client_secret=${Keys.STRAVA_CLIENT_SECRET}" +
                "&refresh_token=${Keys.STRVA_REFRESH_TOKEN}" +
                "&grant_type=refresh_token"

        val queue = Volley.newRequestQueue(listener.getCurrentContext())
        val stringRequest = StringRequest(
            Request.Method.POST, url,
            { response ->
                val stringResponse = JSONObject(response)
                val accessToken = stringResponse.getString("access_token")
                continuation.resume(accessToken)
            },
            { error ->
                continuation.resume(null)
            })

        queue.add(stringRequest)
    }

    fun getActivityInfo(accessToken: String) {

        val url = "https://www.strava.com/api/v3/athlete/activities?access_token=$accessToken"
        val queue = Volley.newRequestQueue(listener.getCurrentContext())
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val data = parseDataFromStrava(response)
                Log.d("MyLog","response: $response")
                listener.onStravaDataReady(data)

            }, {
            })
        queue.add(stringRequest)

    }

    fun parseDataFromStrava(response: String): StravaDataModel {
        val jsonArray = JSONArray(response)
        val stravaDataModel = StravaDataModel()

        for (i in 0 until jsonArray.length()) {
            val mainObject = jsonArray.getJSONObject(i)
            val type = mainObject.getString("type")

            if (type == "Run") {
                val distance = mainObject.getString("distance")
                val name = mainObject.getString("name")
                val movingTime = mainObject.getString("moving_time")


                val parsedDataModel = RunningDataModel(
                    name = name,
                    distance = distance,
                    movingTime = movingTime,
                    type = type,
                )
//                list.add(parsedDataModel)
                stravaDataModel.runningDataModel = parsedDataModel
                break
               // return parsedDataModel
            }
        }
        for (i in 0 until jsonArray.length()) {
            val mainObject = jsonArray.getJSONObject(i)
            val type = mainObject.getString("type")


            if (type == "Ride") {
                val distance = mainObject.getString("distance")
                val name = mainObject.getString("name")
                val movingTime = mainObject.getString("moving_time")
                val avgSpeed = mainObject.getString("average_speed")

                val parsedDataModel = CyclingDataModel(
                    name = name,
                    distance = distance,
                    movingTime = movingTime,
                    type = type,
                    avgSpeed = avgSpeed
                )

                stravaDataModel.cyclingDataModel = parsedDataModel
                break
            }
        }
        Log.d("MyLog", "list: ${stravaDataModel}")
        return stravaDataModel
    }

}