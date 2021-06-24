package com.renbin.sunnyweather.logic.network

import com.renbin.baselibrary.BaseApplication
import com.renbin.sunnyweather.logic.model.DailyResponse
import com.renbin.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *data:2021/6/23
 *Author:renbin
 */
interface WeatherService {

    @GET("v2.5/${BaseApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>
    @GET("v2.5/${BaseApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}