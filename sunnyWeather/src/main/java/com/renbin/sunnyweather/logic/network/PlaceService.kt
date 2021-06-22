package com.renbin.sunnyweather.logic.network
import com.renbin.baselibrary.BaseApplication
import com.renbin.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *data:2021/6/22
 *Author:renbin
 */
interface PlaceService {

    @GET("v2/place?token=${BaseApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}