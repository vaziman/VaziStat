package com.example.myapplication

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.constants.Keys
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.interfaces.IStravaLoader
import com.example.myapplication.models.*
import org.json.JSONArray
import org.json.JSONObject


class RequestStravaData(private val listener: IStravaLoader) {

    //    lateinit var accessToken: String
//    lateinit var listOfStravaData: List<String>
    val homeFragment = HomeFragment()

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
            }, {
//                 Log.d("MyLog", "Response: $it")
            })
        queue.add(stringRequest)
    }

    fun refreshToken(callback: (String?) -> Unit) {

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
                callback.invoke(accessToken)
            },
            { error ->
                callback.invoke(null)
            })
        queue.add(stringRequest)

    }

    fun getActivityInfo(accessToken: String) {

        val url = "https://www.strava.com/api/v3/athlete/activities?access_token=$accessToken"
        val queue = Volley.newRequestQueue(listener.getCurrentContext())
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val data = parseDataFromStrava(response)
                Log.d("MyLog", "response: $response")
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
                val startDate = mainObject.getString("start_date")
                val locationCountry = mainObject.getString("location_country")
                val avgCadence = mainObject.getString("average_cadence")
                val avgTemp = mainObject.getString("average_temp")
                val hasHeartRate = mainObject.getString("has_heartrate") //Boolean
                val avgHeartRate = mainObject.getString("average_heartrate")
                val maxHeartRate = mainObject.getString("max_heartrate")
                val elevGain = mainObject.getString("total_elevation_gain")
                val elevHigh = mainObject.getString("elev_high")
                val elevLow = mainObject.getString("elev_low")
                val idOfActivity = mainObject.getString("id")
//                val caloriesBurned = mainObject.getString("calories")
                // val caloriesBurned = mainObject.getString("calories")


                val parsedDataModel = RunningDataModel(
                    name = name,
                    distance = distance,
                    movingTime = movingTime,
                    type = type,
                    startDate = startDate,
                    locationCountry = locationCountry,
                    avgCadence = avgCadence,
                    avgTemp = avgTemp,
                    hasHeartRate = hasHeartRate.toString(),
                    avgHeartRate = avgHeartRate,
                    maxHeartRate = maxHeartRate,
                    elevGain = elevGain,
                    elevHigh = elevHigh,
                    elevLow = elevLow,
                    idOfActivity = idOfActivity,
                )
//                homeFragment.initRunEntityVariable(parsedDataModel.toRunningEntity())
//                Log.d("MyLog", "runEntityData: ${runEntity }}")
                stravaDataModel.runningDataModel = parsedDataModel
                break

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
                val maxSpeed = mainObject.getString("max_speed")
                val startDate = mainObject.getString("start_date")
                val locationCountry = mainObject.getString("location_country")
                val avgTemp = mainObject.getString("average_temp")
                val hasHeartRate = mainObject.getString("has_heartrate") //Boolean
                val avgHeartRate = mainObject.getString("average_heartrate")
                val maxHeartRate = mainObject.getString("max_heartrate")
                val elevGain = mainObject.getString("total_elevation_gain")
                val elevHigh = mainObject.getString("elev_high")
                val elevLow = mainObject.getString("elev_low")
                val idOfActivity = mainObject.getString("id")
//                val caloriesBurned = mainObject.getString("calories")


                val parsedDataModel = CyclingDataModel(
                    name = name,
                    distance = distance,
                    movingTime = movingTime,
                    type = type,
                    avgSpeed = avgSpeed,
                    maxSpeed = maxSpeed,
                    startDate = startDate,
                    locationCountry = locationCountry,
                    avgTemp = avgTemp,
                    hasHeartRate = hasHeartRate,
                    avgHeartRate = avgHeartRate.toString(),
                    maxHeartRate = maxHeartRate,
                    elevGain = elevGain,
                    elevHigh = elevHigh,
                    elevLow = elevLow,
                    idOfActivity = idOfActivity
                )
//                homeFragment.initCyclingEntityVariable(parsedDataModel.toCyclingEntity())

                stravaDataModel.cyclingDataModel = parsedDataModel
                break
            }
        }
        return stravaDataModel
    }

}