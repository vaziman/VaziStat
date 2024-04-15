package com.example.myapplication

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.models.RunningDataModel
import com.example.myapplication.constants.Keys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject


interface IStravaLoader{
    fun onStravaDataReady(data: ArrayList<RunningDataModel>)
    fun getCurrentContext(): Context
}
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

    fun refreshToken() {
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
                getActivitiesInfo(accessToken)

            }, {
//                Log.d("MyLog", "Response it: $it")
            })

        queue.add(stringRequest)
    }

    fun getActivitiesInfo(accessToken: String) {

        val url = "https://www.strava.com/api/v3/athlete/activities?access_token=$accessToken"
        val queue = Volley.newRequestQueue(listener.getCurrentContext())
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val data = parseDataFromStrava(response)
//                withContext(Dispatchers.Main){
//                }
                listener.onStravaDataReady(data)

            }, {
//                 Log.d("MyLog", "Response: $it")
            })
        queue.add(stringRequest)

    }

    fun parseDataFromStrava(response: String): ArrayList<RunningDataModel> {
        val jsonArray = JSONArray(response)
        val list = ArrayList<RunningDataModel>()

        for (i in 0 until jsonArray.length()) {
            val mainObject = jsonArray.getJSONObject(i)
            val type = mainObject.getString("type")

            if (type == "Run") {
                val distance = mainObject.getString("distance")
                val name = mainObject.getString("name")
                val movingTime = mainObject.getString("moving_time")

                Log.d("MyLog", "name: ${name}")
                Log.d("MyLog", "type: ${type}")
                Log.d("MyLog", "distance: ${distance}")
                Log.d("MyLog", "movingTime: ${movingTime}")

                val parsedDataModel = RunningDataModel(
                    name = name,
                    distance = distance,
                    movingTime = movingTime,
                    type = type,
                )
                list.add(parsedDataModel)
                break
            }
        }
        return list
    }

}