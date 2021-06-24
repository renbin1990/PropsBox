package com.renbin.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *data:2021/6/22
 *Author:renbin
 */
object SunnyWeatherNetwork {

    //创建一个PlaceService接口的动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()
    private val weatherService = ServiceCreator.create(WeatherService::class.java)
    //定义一个searchPlaces()函数，并在这里调用刚刚在PlaceService接口中定义的 searchPlaces()方法，以发起搜索城市数据请求。
    suspend fun searchPlaces(query : String) = placeService.searchPlaces(query).await()
    suspend fun getDailyWeather(lng: String, lat: String) = weatherService.getDailyWeather(lng, lat).await()
    suspend fun getRealtimeWeather(lng: String, lat: String) = weatherService.getRealtimeWeather(lng, lat).await()

    private suspend fun <T> Call<T>.await() : T{
        return suspendCoroutine {
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) it.resume(body)
                    else it.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }
}