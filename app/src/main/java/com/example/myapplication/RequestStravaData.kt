package com.example.myapplication

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.constants.Keys
import org.json.JSONObject

class RequestStravaData(private val context: Context) {

    fun getStravaAutorisationCode() {

        val urlAuthorizeCode = "https://www.strava.com/oauth/token?client_id=${Keys.STRAVA_CLIENT_ID}" +
                "&client_secret=${Keys.STRAVA_CLIENT_SECRET}" +
                "&code=${Keys.STRAVA_AUTORISATION_CODE}" +
                "&grant_type=authorization_code"
        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.POST, urlAuthorizeCode,
            { response ->
                val stringResponse = JSONObject(response)
                val accessToken = stringResponse.getJSONObject("access_token")

                // Log.d("MyLog", "Response: $stringResponse")
                //Log.d("MyLog", "Acceess_token: $accessToken")
            }, {
                // Log.d("MyLog", "Response: $it")
            })
        queue.add(stringRequest)
    }

     fun refreshToken(){
        val url = "https://www.strava.com/oauth/token" +
                "?client_id=${Keys.STRAVA_CLIENT_ID}" +
                "&client_secret=${Keys.STRAVA_CLIENT_SECRET}" +
                "&refresh_token=${Keys.STRVA_REFRESH_TOKEN}" +
                "&grant_type=refresh_token"

        val queue = Volley.newRequestQueue(context)
        val stringReqest = StringRequest(
            Request.Method.POST, url,
            {
                    response ->
                val stringResponse = JSONObject(response)
                val accessToken = stringResponse.getString("access_token")
                // val accessToken = stringResponse.getJSONObject("access_token")
                Log.d("MyLog", "Response: $stringResponse")
                Log.d("MyLog", "Access_token: $accessToken")
                getActivitiesInfo(accessToken)
            },{
                Log.d("MyLog", "Response: $it")
            })
        queue.add(stringReqest)

    }

     fun getActivitiesInfo(accessToken: String){
         val url = "https://www.strava.com/api/v3/athlete/activities?access_token=$accessToken"
         val queue = Volley.newRequestQueue(context)
         val stringRequest = StringRequest(Request.Method.GET, url,
             {
                 response ->
                 Log.d("MyLog", "Response: $response")
             }, {
                 Log.d("MyLog", "Response: $it")
             })
         queue.add(stringRequest)

    }
}